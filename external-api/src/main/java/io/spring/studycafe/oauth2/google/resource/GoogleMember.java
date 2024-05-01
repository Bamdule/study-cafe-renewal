package io.spring.studycafe.oauth2.google.resource;

public record GoogleMember(
    String id,
    String nickname,
    String email,
    String name
) {
}

//  "id": "103707946781717949594",
//      "email": "dsds550@gmail.com",
//      "verified_email": true,
//      "name": "윤성호",
//      "given_name": "성호",
//      "family_name": "윤",
//      "picture": "https://lh3.googleusercontent.com/a/AEdFTp5OW4CIt13eg5K6j6v4Ma03Bm1dgtwKlpP3PUo7=s96-c",
//      "locale": "ko"