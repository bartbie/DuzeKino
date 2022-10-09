import {Fetcher} from 'swr';

export const fetcher: Fetcher<any, string> = (...args) => fetch(...args).then((res) => res.json())
export const BACKEND_URL = "http://localhost:8080/api/v1/"
