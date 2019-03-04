<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
version="1.0">
<xsl:template match="/">
<html>
<head>
    <title>
        <xsl:value-of select="view/header/title"/>
    </title>
</head>
<body>
    <form>
        <xsl:attribute name="name"><xsl:value-of select="view/body/form/name" /></xsl:attribute>
        <xsl:attribute name="action"><xsl:value-of select="view/body/form/action" /></xsl:attribute>
        <xsl:attribute name="method"><xsl:value-of select="view/body/form/method" /></xsl:attribute>
        <xsl:for-each select="view/body/form/textView">
            <xsl:value-of select="label" />：<xsl:value-of select="value" /><br/>
        </xsl:for-each>
        <xsl:value-of select="view/body/form/checkBoxView/label" />：
        <xsl:for-each select="view/body/form/checkBoxView/box">
            <input>
                <xsl:attribute name="type"><xsl:value-of select="../type" /></xsl:attribute>
                <xsl:attribute name="name"><xsl:value-of select="../name" /></xsl:attribute>
                <xsl:attribute name="value"><xsl:value-of select="value" /></xsl:attribute>
                <xsl:if test="checked = 1">
                    <xsl:attribute name="checked">checked</xsl:attribute>
                </xsl:if>
            </input>
            <xsl:value-of select="text" />
        </xsl:for-each>
        <br/>
        <xsl:value-of select="view/body/form/selectView/label" />：
        <select>
            <xsl:attribute name="name"><xsl:value-of select="view/body/form/selectView/name" /></xsl:attribute>
            <xsl:for-each select="view/body/form/selectView/option">
                <option>
                    <xsl:attribute name="value"><xsl:value-of select="value" /></xsl:attribute>
                    <xsl:value-of select="text" />
                </option>
            </xsl:for-each>
        </select>
        <br/>
        <input>
            <xsl:attribute name="name"><xsl:value-of select="view/body/form/buttonView/name" /></xsl:attribute>
            <xsl:attribute name="type">submit</xsl:attribute>
            <xsl:attribute name="value"><xsl:value-of select="view/body/form/buttonView/label" /></xsl:attribute>
        </input>
    </form>
</body>
</html>
</xsl:template>
</xsl:stylesheet>