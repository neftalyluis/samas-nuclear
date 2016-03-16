__precompile__(true)

"""
# SAMAS
Julia package to access SAMAS API.

"""
module samas

export set_api_key,
       SAMASException,

using DataFrames: DataFrame, eachrow, readtable
using HttpCommon: Response
using   Requests: json, post

import Base: showerror

typealias String AbstractString

"`SAMAS` Exception."
immutable SAMASException <: Exception
    response::Response
end

function showerror(io::IO, ex::SAMASException)
    endpoint = match(r"sync/(.+)/", get(ex.response.request).resource)[1]
    println(io, typeof(ex), ": $endpoint")
    for (k, v) in json(ex.response)
        print(io, ucfirst(k), ": ")
        println(io, v)
    end
end

"Main `SAMAS` function, used to call **SAMAS** API."
function call_SAMAS(
        endpoint        :: String,
        async           :: Bool;
        api_url         :: String = "https://api.samas.mx",
        version         :: Int    = 1,
        default_version :: Int    = 1,
        options         :: Dict   = Dict()
    )
    try
        options["apikey"] = _SAMAS_API_KEY
    catch
        error("Use `SAMAS.set_api_key(api_key::AbstractString)` first.")
    end
    sync_str = async ? "async" : "sync"
    r = post(
        "$(api_url)/$(version)/api/$(sync_str)/$(endpoint)/v$(default_version)",
        data = options
    )
    return r.status == 200 ? json(r) : throw(SAMASException(r))
end

"""
Sets the **SAMAS** API key, this function must be called after
`using SAMAS` with a valid key, in order to be able to use the SAMAS *API*.
"""
function set_api_key(api_key::String)
    global _SAMAS_API_KEY
    const _SAMAS_API_KEY = api_key
    return nothing
end

"""
`DataFrame` that holds the `SAMAS` data used to metaprogram a wrapper
function for each endpoint in the API.
"""
const SAMAS_API = readtable(
    joinpath(Pkg.dir("SAMAS"), "src", "api.data"),
    separator = ' ',
)

# Meta wrap the API:
for row in eachrow(SAMAS_API::DataFrame)
    func_name   = row[:Func_Name]
    endpoint    = row[:Endpoint]
    async_only  = row[:Async_Only]
    description = row[:Description]
    title = join([ucfirst(s) for s in split(func_name, '_')], ' ')
    docstring = (

    )
    @eval begin
        @doc $docstring ->
        function $(symbol(func_name))(; kwargs...)
            return call_SAMAS($endpoint, $async_only, options = Dict(kwargs))
        end
    end
end

end
