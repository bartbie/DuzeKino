export const BACKEND_URL = "localhost:8080/api/v1/"

export type Method = "POST" | "GET" | "PUT" | "DELETE" 

export async function sendRequest(endpoint: string, method: Method, body?: object | null): Promise<any> {
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