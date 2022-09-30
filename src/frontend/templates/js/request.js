export const URL = "http://localhost:8080";

/**
 *
 * @param endpoint {string} endpoint at backend
 * @param method {"POST" | "GET" | "PUT" | "DELETE"} "POST", "GET" etc.
 * @param body {Object | null} object that will be stringified and sent with request; null when fetching
 * @returns {Object}
 * @throws {Error} when response not ok
 */
export function sendRequest(endpoint, method, body) {
    const async_insides = async () => {
        let response = await fetch(URL + endpoint, {
            method: method,
            body: JSON.stringify(body),
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
    let x = null;
    async_insides().catch((reason) => {
        throw new Error(reason)
    }).then((data) => x = data)
    return x;

}