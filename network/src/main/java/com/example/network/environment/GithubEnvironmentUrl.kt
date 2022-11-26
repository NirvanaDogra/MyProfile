package com.example.network.environment

object GithubEnvironmentUrl {
    private const val HTTPS = "https://"
    private const val SLASH = "/"
    const val BASEURL = HTTPS + "nirvanadogra.github.io" + SLASH
    private const val WHATS_NEW_POST_ENDPOINT = "/whats-new-post.json"
    const val WHATS_NEW_POST_URL = BASEURL + WHATS_NEW_POST_ENDPOINT
}