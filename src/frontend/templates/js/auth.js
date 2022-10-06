import {sendRequest} from "./request.js";

export async function registerUser(user) {
    return await sendRequest("/registration", "POST", user);
}

export async function loginUser(user) {
    return await sendRequest("/login", "POST", user);
}
