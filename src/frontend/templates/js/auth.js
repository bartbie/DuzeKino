import {sendRequest} from "./request.js";

export function registerUser(user) {
    return sendRequest("/registration", "POST", user);
}

export function loginUser(user) {
    return sendRequest("/login", "POST", user);
}
