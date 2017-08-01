<html>
<body>
<pre>
    hello

</pre>
<#--123-->
${value}
<#list Colors as aa>
    ${aa}
</#list>
<#list Maps?keys as x>
    ${x}  ${Maps[x]}
</#list>
${User.name}
${User.getName()}
<#assign title="李四">
${title}<br>
<#include "/header.ftl"/><br>
<#include "/header.ftl"parse=true>

<#macro render_color colors >
    Color By ${colors}<br>
</#macro>
<#macro render_color colors index >
Color By ${colors} ${index}<br>
</#macro>
<#list Colors as aa>
    <@render_color colors=aa index =aa_index/>
</#list>
<#assign helloworld2 = "${title} world">
${helloworld2}
${Colors?size}
</body>
</html>