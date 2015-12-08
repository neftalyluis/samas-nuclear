/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.entities;

import java.beans.*;

/**
 *
 * @author DMCT
 */
public class BondBeanInfo extends SimpleBeanInfo {

    // Bean descriptor//GEN-FIRST:BeanDescriptor
    /*lazy BeanDescriptor*/
    private static BeanDescriptor getBdescriptor(){
        BeanDescriptor beanDescriptor = new BeanDescriptor  ( mx.samas.ejb.entities.Bond.class , null ); // NOI18N//GEN-HEADEREND:BeanDescriptor
        // Here you can add code for customizing the BeanDescriptor.

        return beanDescriptor;     }//GEN-LAST:BeanDescriptor


    // Property identifiers//GEN-FIRST:Properties
    private static final int PROPERTY_amortizing = 0;
    private static final int PROPERTY_bondCollateral = 1;
    private static final int PROPERTY_callable = 2;
    private static final int PROPERTY_cashflowDates = 3;
    private static final int PROPERTY_currencyDenomination = 4;
    private static final int PROPERTY_dayCount = 5;
    private static final int PROPERTY_faceValue = 6;
    private static final int PROPERTY_fixingDate = 7;
    private static final int PROPERTY_id = 8;
    private static final int PROPERTY_isin = 9;
    private static final int PROPERTY_issuer = 10;
    private static final int PROPERTY_maturityDate = 11;
    private static final int PROPERTY_name = 12;
    private static final int PROPERTY_referenceRate = 13;
    private static final int PROPERTY_series = 14;
    private static final int PROPERTY_settlementTimes = 15;
    private static final int PROPERTY_termStructure = 16;
    private static final int PROPERTY_ticker = 17;
    private static final int PROPERTY_tickSize = 18;
    private static final int PROPERTY_tv = 19;
    private static final int PROPERTY_vectors = 20;

    // Property array 
    /*lazy PropertyDescriptor*/
    private static PropertyDescriptor[] getPdescriptor(){
        PropertyDescriptor[] properties = new PropertyDescriptor[21];
    
        try {
            properties[PROPERTY_amortizing] = new PropertyDescriptor ( "amortizing", mx.samas.ejb.entities.Bond.class, null, "setAmortizing" ); // NOI18N
            properties[PROPERTY_bondCollateral] = new PropertyDescriptor ( "bondCollateral", mx.samas.ejb.entities.Bond.class, "getBondCollateral", "setBondCollateral" ); // NOI18N
            properties[PROPERTY_callable] = new PropertyDescriptor ( "callable", mx.samas.ejb.entities.Bond.class, null, "setCallable" ); // NOI18N
            properties[PROPERTY_cashflowDates] = new PropertyDescriptor ( "cashflowDates", mx.samas.ejb.entities.Bond.class, "getCashflowDates", "setCashflowDates" ); // NOI18N
            properties[PROPERTY_currencyDenomination] = new PropertyDescriptor ( "currencyDenomination", mx.samas.ejb.entities.Bond.class, "getCurrencyDenomination", "setCurrencyDenomination" ); // NOI18N
            properties[PROPERTY_dayCount] = new PropertyDescriptor ( "dayCount", mx.samas.ejb.entities.Bond.class, "getDayCount", "setDayCount" ); // NOI18N
            properties[PROPERTY_faceValue] = new PropertyDescriptor ( "faceValue", mx.samas.ejb.entities.Bond.class, "getFaceValue", "setFaceValue" ); // NOI18N
            properties[PROPERTY_fixingDate] = new PropertyDescriptor ( "fixingDate", mx.samas.ejb.entities.Bond.class, "getFixingDate", "setFixingDate" ); // NOI18N
            properties[PROPERTY_id] = new PropertyDescriptor ( "id", mx.samas.ejb.entities.Bond.class, "getId", "setId" ); // NOI18N
            properties[PROPERTY_isin] = new PropertyDescriptor ( "isin", mx.samas.ejb.entities.Bond.class, "getIsin", "setIsin" ); // NOI18N
            properties[PROPERTY_issuer] = new PropertyDescriptor ( "issuer", mx.samas.ejb.entities.Bond.class, null, "setIssuer" ); // NOI18N
            properties[PROPERTY_maturityDate] = new PropertyDescriptor ( "maturityDate", mx.samas.ejb.entities.Bond.class, "getMaturityDate", "setMaturityDate" ); // NOI18N
            properties[PROPERTY_name] = new PropertyDescriptor ( "name", mx.samas.ejb.entities.Bond.class, "getName", "setName" ); // NOI18N
            properties[PROPERTY_referenceRate] = new PropertyDescriptor ( "referenceRate", mx.samas.ejb.entities.Bond.class, "getReferenceRate", "setReferenceRate" ); // NOI18N
            properties[PROPERTY_series] = new PropertyDescriptor ( "series", mx.samas.ejb.entities.Bond.class, "getSeries", "setSeries" ); // NOI18N
            properties[PROPERTY_settlementTimes] = new PropertyDescriptor ( "settlementTimes", mx.samas.ejb.entities.Bond.class, "getSettlementTimes", "setSettlementTimes" ); // NOI18N
            properties[PROPERTY_termStructure] = new PropertyDescriptor ( "termStructure", mx.samas.ejb.entities.Bond.class, "getTermStructure", "setTermStructure" ); // NOI18N
            properties[PROPERTY_ticker] = new PropertyDescriptor ( "ticker", mx.samas.ejb.entities.Bond.class, "getTicker", "setTicker" ); // NOI18N
            properties[PROPERTY_tickSize] = new PropertyDescriptor ( "tickSize", mx.samas.ejb.entities.Bond.class, "getTickSize", "setTickSize" ); // NOI18N
            properties[PROPERTY_tv] = new PropertyDescriptor ( "tv", mx.samas.ejb.entities.Bond.class, null, "setTv" ); // NOI18N
            properties[PROPERTY_vectors] = new PropertyDescriptor ( "vectors", mx.samas.ejb.entities.Bond.class, "getVectors", "setVectors" ); // NOI18N
        }
        catch(IntrospectionException e) {
            e.printStackTrace();
        }//GEN-HEADEREND:Properties
        // Here you can add code for customizing the properties array.

        return properties;     }//GEN-LAST:Properties

    // EventSet identifiers//GEN-FIRST:Events

    // EventSet array
    /*lazy EventSetDescriptor*/
    private static EventSetDescriptor[] getEdescriptor(){
        EventSetDescriptor[] eventSets = new EventSetDescriptor[0];//GEN-HEADEREND:Events
        // Here you can add code for customizing the event sets array.

        return eventSets;     }//GEN-LAST:Events

    // Method identifiers//GEN-FIRST:Methods
    private static final int METHOD_equals0 = 0;
    private static final int METHOD_getIssuer1 = 1;
    private static final int METHOD_getSerialVersionUID2 = 2;
    private static final int METHOD_getTv3 = 3;
    private static final int METHOD_hashCode4 = 4;
    private static final int METHOD_isAmortizing5 = 5;
    private static final int METHOD_isCallable6 = 6;
    private static final int METHOD_setIssuer7 = 7;
    private static final int METHOD_setTv8 = 8;
    private static final int METHOD_toString9 = 9;

    // Method array 
    /*lazy MethodDescriptor*/
    private static MethodDescriptor[] getMdescriptor(){
        MethodDescriptor[] methods = new MethodDescriptor[10];
    
        try {
            methods[METHOD_equals0] = new MethodDescriptor(mx.samas.ejb.entities.Bond.class.getMethod("equals", new Class[] {java.lang.Object.class})); // NOI18N
            methods[METHOD_equals0].setDisplayName ( "" );
            methods[METHOD_getIssuer1] = new MethodDescriptor(mx.samas.ejb.entities.Asset.class.getMethod("getIssuer", new Class[] {})); // NOI18N
            methods[METHOD_getIssuer1].setDisplayName ( "" );
            methods[METHOD_getSerialVersionUID2] = new MethodDescriptor(mx.samas.ejb.entities.Asset.class.getMethod("getSerialVersionUID", new Class[] {})); // NOI18N
            methods[METHOD_getSerialVersionUID2].setDisplayName ( "" );
            methods[METHOD_getTv3] = new MethodDescriptor(mx.samas.ejb.entities.Asset.class.getMethod("getTv", new Class[] {})); // NOI18N
            methods[METHOD_getTv3].setDisplayName ( "" );
            methods[METHOD_hashCode4] = new MethodDescriptor(mx.samas.ejb.entities.Bond.class.getMethod("hashCode", new Class[] {})); // NOI18N
            methods[METHOD_hashCode4].setDisplayName ( "" );
            methods[METHOD_isAmortizing5] = new MethodDescriptor(mx.samas.ejb.entities.Bond.class.getMethod("isAmortizing", new Class[] {})); // NOI18N
            methods[METHOD_isAmortizing5].setDisplayName ( "" );
            methods[METHOD_isCallable6] = new MethodDescriptor(mx.samas.ejb.entities.Bond.class.getMethod("isCallable", new Class[] {})); // NOI18N
            methods[METHOD_isCallable6].setDisplayName ( "" );
            methods[METHOD_setIssuer7] = new MethodDescriptor(mx.samas.ejb.entities.Asset.class.getMethod("setIssuer", new Class[] {java.lang.String.class})); // NOI18N
            methods[METHOD_setIssuer7].setDisplayName ( "" );
            methods[METHOD_setTv8] = new MethodDescriptor(mx.samas.ejb.entities.Asset.class.getMethod("setTv", new Class[] {java.lang.String.class})); // NOI18N
            methods[METHOD_setTv8].setDisplayName ( "" );
            methods[METHOD_toString9] = new MethodDescriptor(mx.samas.ejb.entities.Bond.class.getMethod("toString", new Class[] {})); // NOI18N
            methods[METHOD_toString9].setDisplayName ( "" );
        }
        catch( Exception e) {}//GEN-HEADEREND:Methods
        // Here you can add code for customizing the methods array.

        return methods;     }//GEN-LAST:Methods

    private static java.awt.Image iconColor16 = null;//GEN-BEGIN:IconsDef
    private static java.awt.Image iconColor32 = null;
    private static java.awt.Image iconMono16 = null;
    private static java.awt.Image iconMono32 = null;//GEN-END:IconsDef
    private static String iconNameC16 = null;//GEN-BEGIN:Icons
    private static String iconNameC32 = null;
    private static String iconNameM16 = null;
    private static String iconNameM32 = null;//GEN-END:Icons

    private static final int defaultPropertyIndex = -1;//GEN-BEGIN:Idx
    private static final int defaultEventIndex = -1;//GEN-END:Idx


//GEN-FIRST:Superclass
    // Here you can add code for customizing the Superclass BeanInfo.
    /**
     * @return the PROPERTY_amortizing
     */
    public static int getPROPERTY_amortizing() {
        return PROPERTY_amortizing;
    }

    /**
     * @return the PROPERTY_bondCollateral
     */
    public static int getPROPERTY_bondCollateral() {
        return PROPERTY_bondCollateral;
    }

    /**
     * @return the PROPERTY_callable
     */
    public static int getPROPERTY_callable() {
        return PROPERTY_callable;
    }

    /**
     * @return the PROPERTY_cashflowDates
     */
    public static int getPROPERTY_cashflowDates() {
        return PROPERTY_cashflowDates;
    }

    /**
     * @return the PROPERTY_currencyDenomination
     */
    public static int getPROPERTY_currencyDenomination() {
        return PROPERTY_currencyDenomination;
    }

    /**
     * @return the PROPERTY_dayCount
     */
    public static int getPROPERTY_dayCount() {
        return PROPERTY_dayCount;
    }

    /**
     * @return the PROPERTY_faceValue
     */
    public static int getPROPERTY_faceValue() {
        return PROPERTY_faceValue;
    }

    /**
     * @return the PROPERTY_fixingDate
     */
    public static int getPROPERTY_fixingDate() {
        return PROPERTY_fixingDate;
    }

    /**
     * @return the PROPERTY_id
     */
    public static int getPROPERTY_id() {
        return PROPERTY_id;
    }

    /**
     * @return the PROPERTY_isin
     */
    public static int getPROPERTY_isin() {
        return PROPERTY_isin;
    }

    /**
     * @return the PROPERTY_issuer
     */
    public static int getPROPERTY_issuer() {
        return PROPERTY_issuer;
    }

    /**
     * @return the PROPERTY_maturityDate
     */
    public static int getPROPERTY_maturityDate() {
        return PROPERTY_maturityDate;
    }

    /**
     * @return the PROPERTY_name
     */
    public static int getPROPERTY_name() {
        return PROPERTY_name;
    }

    /**
     * @return the PROPERTY_referenceRate
     */
    public static int getPROPERTY_referenceRate() {
        return PROPERTY_referenceRate;
    }

    /**
     * @return the PROPERTY_series
     */
    public static int getPROPERTY_series() {
        return PROPERTY_series;
    }

    /**
     * @return the PROPERTY_settlementTimes
     */
    public static int getPROPERTY_settlementTimes() {
        return PROPERTY_settlementTimes;
    }

    /**
     * @return the PROPERTY_termStructure
     */
    public static int getPROPERTY_termStructure() {
        return PROPERTY_termStructure;
    }

    /**
     * @return the PROPERTY_ticker
     */
    public static int getPROPERTY_ticker() {
        return PROPERTY_ticker;
    }

    /**
     * @return the PROPERTY_tickSize
     */
    public static int getPROPERTY_tickSize() {
        return PROPERTY_tickSize;
    }

    /**
     * @return the PROPERTY_tv
     */
    public static int getPROPERTY_tv() {
        return PROPERTY_tv;
    }

    /**
     * @return the PROPERTY_vectors
     */
    public static int getPROPERTY_vectors() {
        return PROPERTY_vectors;
    }

    /**
     * @return the METHOD_equals0
     */
    public static int getMETHOD_equals0() {
        return METHOD_equals0;
    }

    /**
     * @return the METHOD_getIssuer1
     */
    public static int getMETHOD_getIssuer1() {
        return METHOD_getIssuer1;
    }

    /**
     * @return the METHOD_getSerialVersionUID2
     */
    public static int getMETHOD_getSerialVersionUID2() {
        return METHOD_getSerialVersionUID2;
    }

    /**
     * @return the METHOD_getTv3
     */
    public static int getMETHOD_getTv3() {
        return METHOD_getTv3;
    }

    /**
     * @return the METHOD_hashCode4
     */
    public static int getMETHOD_hashCode4() {
        return METHOD_hashCode4;
    }

    /**
     * @return the METHOD_isAmortizing5
     */
    public static int getMETHOD_isAmortizing5() {
        return METHOD_isAmortizing5;
    }

    /**
     * @return the METHOD_isCallable6
     */
    public static int getMETHOD_isCallable6() {
        return METHOD_isCallable6;
    }

    /**
     * @return the METHOD_setIssuer7
     */
    public static int getMETHOD_setIssuer7() {
        return METHOD_setIssuer7;
    }

    /**
     * @return the METHOD_setTv8
     */
    public static int getMETHOD_setTv8() {
        return METHOD_setTv8;
    }

    /**
     * @return the METHOD_toString9
     */
    public static int getMETHOD_toString9() {
        return METHOD_toString9;
    }

    /**
     * @return the iconColor16
     */
    public static java.awt.Image getIconColor16() {
        return iconColor16;
    }

    /**
     * @param aIconColor16 the iconColor16 to set
     */
    public static void setIconColor16(java.awt.Image aIconColor16) {
        iconColor16 = aIconColor16;
    }

    /**
     * @return the iconColor32
     */
    public static java.awt.Image getIconColor32() {
        return iconColor32;
    }

    /**
     * @param aIconColor32 the iconColor32 to set
     */
    public static void setIconColor32(java.awt.Image aIconColor32) {
        iconColor32 = aIconColor32;
    }

    /**
     * @return the iconMono16
     */
    public static java.awt.Image getIconMono16() {
        return iconMono16;
    }

    /**
     * @param aIconMono16 the iconMono16 to set
     */
    public static void setIconMono16(java.awt.Image aIconMono16) {
        iconMono16 = aIconMono16;
    }

    /**
     * @return the iconMono32
     */
    public static java.awt.Image getIconMono32() {
        return iconMono32;
    }

    /**
     * @param aIconMono32 the iconMono32 to set
     */
    public static void setIconMono32(java.awt.Image aIconMono32) {
        iconMono32 = aIconMono32;
    }

    /**
     * @return the iconNameC16
     */
    public static String getIconNameC16() {
        return iconNameC16;
    }

    /**
     * @param aIconNameC16 the iconNameC16 to set
     */
    public static void setIconNameC16(String aIconNameC16) {
        iconNameC16 = aIconNameC16;
    }

    /**
     * @return the iconNameC32
     */
    public static String getIconNameC32() {
        return iconNameC32;
    }

    /**
     * @param aIconNameC32 the iconNameC32 to set
     */
    public static void setIconNameC32(String aIconNameC32) {
        iconNameC32 = aIconNameC32;
    }

    /**
     * @return the iconNameM16
     */
    public static String getIconNameM16() {
        return iconNameM16;
    }

    /**
     * @param aIconNameM16 the iconNameM16 to set
     */
    public static void setIconNameM16(String aIconNameM16) {
        iconNameM16 = aIconNameM16;
    }

    /**
     * @return the iconNameM32
     */
    public static String getIconNameM32() {
        return iconNameM32;
    }

    /**
     * @param aIconNameM32 the iconNameM32 to set
     */
    public static void setIconNameM32(String aIconNameM32) {
        iconNameM32 = aIconNameM32;
    }

//GEN-LAST:Superclass
    /**
     * Gets the bean's <code>BeanDescriptor</code>s.
     *
     * @return BeanDescriptor describing the editable properties of this bean.
     * May return null if the information should be obtained by automatic
     * analysis.
     */
    @Override
    public BeanDescriptor getBeanDescriptor() {
        return getBdescriptor();
    }

    /**
     * Gets the bean's <code>PropertyDescriptor</code>s.
     *
     * @return An array of PropertyDescriptors describing the editable
     * properties supported by this bean. May return null if the information
     * should be obtained by automatic analysis.
     * <p>
     * If a property is indexed, then its entry in the result array will belong
     * to the IndexedPropertyDescriptor subclass of PropertyDescriptor. A client
     * of getPropertyDescriptors can use "instanceof" to check if a given
     * PropertyDescriptor is an IndexedPropertyDescriptor.
     */
    @Override
    public PropertyDescriptor[] getPropertyDescriptors() {
        return getPdescriptor();
    }

    /**
     * Gets the bean's <code>EventSetDescriptor</code>s.
     *
     * @return An array of EventSetDescriptors describing the kinds of events
     * fired by this bean. May return null if the information should be obtained
     * by automatic analysis.
     */
    @Override
    public EventSetDescriptor[] getEventSetDescriptors() {
        return getEdescriptor();
    }

    /**
     * Gets the bean's <code>MethodDescriptor</code>s.
     *
     * @return An array of MethodDescriptors describing the methods implemented
     * by this bean. May return null if the information should be obtained by
     * automatic analysis.
     */
    @Override
    public MethodDescriptor[] getMethodDescriptors() {
        return getMdescriptor();
    }

    /**
     * A bean may have a "default" property that is the property that will
     * mostly commonly be initially chosen for update by human's who are
     * customizing the bean.
     *
     * @return Index of default property in the PropertyDescriptor array
     * returned by getPropertyDescriptors.
     * <P>
     * Returns -1 if there is no default property.
     */
    @Override
    public int getDefaultPropertyIndex() {
        return defaultPropertyIndex;
    }

    /**
     * A bean may have a "default" event that is the event that will mostly
     * commonly be used by human's when using the bean.
     *
     * @return Index of default event in the EventSetDescriptor array returned
     * by getEventSetDescriptors.
     * <P>
     * Returns -1 if there is no default event.
     */
    @Override
    public int getDefaultEventIndex() {
        return defaultEventIndex;
    }

    /**
     * This method returns an image object that can be used to represent the
     * bean in toolboxes, toolbars, etc. Icon images will typically be GIFs, but
     * may in future include other formats.
     * <p>
     * Beans aren't required to provide icons and may return null from this
     * method.
     * <p>
     * There are four possible flavors of icons (16x16 color, 32x32 color, 16x16
     * mono, 32x32 mono). If a bean choses to only support a single icon we
     * recommend supporting 16x16 color.
     * <p>
     * We recommend that icons have a "transparent" background so they can be
     * rendered onto an existing background.
     *
     * @param iconKind The kind of icon requested. This should be one of the
     * constant values ICON_COLOR_16x16, ICON_COLOR_32x32, ICON_MONO_16x16, or
     * ICON_MONO_32x32.
     * @return An image object representing the requested icon. May return null
     * if no suitable icon is available.
     */
    @Override
    public java.awt.Image getIcon(int iconKind) {
        switch (iconKind) {
            case ICON_COLOR_16x16:
                if (getIconNameC16() == null) {
                    return null;
                } else {
                    if (getIconColor16() == null) {
                        setIconColor16(loadImage(getIconNameC16()));
                    }
                    return getIconColor16();
                }
            case ICON_COLOR_32x32:
                if (getIconNameC32() == null) {
                    return null;
                } else {
                    if (getIconColor32() == null) {
                        setIconColor32(loadImage(getIconNameC32()));
                    }
                    return getIconColor32();
                }
            case ICON_MONO_16x16:
                if (getIconNameM16() == null) {
                    return null;
                } else {
                    if (getIconMono16() == null) {
                        setIconMono16(loadImage(getIconNameM16()));
                    }
                    return getIconMono16();
                }
            case ICON_MONO_32x32:
                if (getIconNameM32() == null) {
                    return null;
                } else {
                    if (getIconMono32() == null) {
                        setIconMono32(loadImage(getIconNameM32()));
                    }
                    return getIconMono32();
                }
            default:
                return null;
        }
    }

}
