import {sendRequest} from "./request.js";

export const MOVIE_ENDPOINT = "/api/v1/Movie";

/**
 * returns closest backend PG rating string representation for that number
 * @param integer age
 * @returns {"SEVENTEEN" | "FIFTEEN" | "THIRTEEN" | "ANY"}
 */
export function toClosest(integer) {
    if (integer >= 17) {
        return "SEVENTEEN";
    } else if (integer >= 15) {
        return "FIFTEEN";
    } else if (integer >= 13) {
        return "THIRTEEN";
    } else {
        return "ANY";
    }
}

/**
 * returns minutes in ISO 6801 standard
 * @param minutes
 * @returns {string}
 */
export function toISO(minutes) {
    return `PT${minutes}M`;
}

export function movieRequest(method, body) {
    return sendRequest(MOVIE_ENDPOINT, method, body);
}

export function fetchAllMovies() { // returns array of movies
    return movieRequest("GET", null);
}

export function saveMovie(movie) {
    return movieRequest("POST", JSON.stringify(movie));
}