export const URL = "http://localhost:8080";

/**
 *
 * @param endpoint {string} endpoint at backend
 * @param method {"POST" | "GET" | "PUT" | "DELETE"} "POST", "GET" etc.
 * @param body {Object | null} object that will be stringified and sent with request; null when fetching
 * @returns {Promise<Object>}
 * @throws {Error} when response not ok
 */
export async function sendRequest(endpoint, method, body) {
    let response = await fetch(URL + endpoint, {
        method: method,
        body: body == null ? null : JSON.stringify(body),
        headers: {
            "Content-type": "application/json"
        }
    });
    if (!response.ok) {
        const message = `An error has occurred: ${response.status}`;
        throw new Error(message);
    }
    return await response.json();
}