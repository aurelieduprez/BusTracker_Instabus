package com.aurelieduprez.instabus

class Post {

    var Id: Int = 0;
    var StationId: String = ""
    var Content: String = ""
    var Path: String = ""

    constructor(
        StationId: String,
        Content: String,
        Path: String
    ) {
        this.StationId = StationId
        this.Content = Content
        this.Path = Path
    }
}