/*
 * Copyright 2017 JoinFaces.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package mx.samas.rules;

import java.util.logging.Logger;
import org.easyrules.core.BasicRule;

/**
 *
 * @author samas
 */
public class BooleanEqualsRule extends BasicRule {

    private static final Logger LOG = Logger.getLogger(BooleanEqualsRule.class.getName());

    private boolean toTest;

    public BooleanEqualsRule(boolean b) {
        super("Boolean Equals Rule", "Checks if some boolean is true");
        toTest = b;
    }

    @Override
    public boolean evaluate() {
        
        return toTest;
    }

    @Override
    public void execute() throws Exception {
       LOG.info("It's k");
    }

}
