import {sendRequest, Method} from "./request.js";

export const MOVIE_ENDPOINT = "/api/v1/Movie";

export type PG = "SEVENTEEN" | "FIFTEEN" | "THIRTEEN" | "ANY" 

export type ISOMinutes = string;

export type Movie = {
    title: string,
    year: number,
    PG: PG,
    duration: ISOMinutes | number,
    description: string,
    theater: string
}

// returns closest backend PG rating string representation for that number
export function toClosest(integer: number): PG {
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

// returns minutes in ISO 6801 standard
export function toISO(minutes: number): ISOMinutes {
    return `PT${minutes}M`;
}

export async function movieRequest(method: Method, body: Movie | null): Promise<any> {
    return await sendRequest(MOVIE_ENDPOINT, method, body);
}

export async function fetchAllMovies(): Promise<Array<Movie>> { // returns array of movies
    return await movieRequest("GET", null);
}

export async function saveMovie(movie: Movie): Promise<any> {
    return await movieRequest("POST", movie);
}