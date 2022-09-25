package protoscript.smtp.spec

class OAuthSpec(private val authMap:MutableMap<String,String>) {

    fun url(url:String) = this.authMap.put("url", url)
    fun grantType(grantType:String) = this.authMap.put("grant_type", grantType)
    fun username(username:String) = this.authMap.put("username", username)
    fun password(password:String) = this.authMap.put("password", password)
    fun clientSecret(clientSecret:String) = this.authMap.put("client_secret", clientSecret)
    fun clientId(clientId:String) = this.authMap.put("client_id", clientId)
    fun scope(scope:String) = this.authMap.put("scope", scope)


}