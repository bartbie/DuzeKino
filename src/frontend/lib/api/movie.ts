import useSWR from 'swr';

import { fetcher, BACKEND_URL } from './request';
import { Movie } from '../movie';

export const MOVIE_ENDPOINT = BACKEND_URL+"Movie";
// const MOVIE_ENDPOINT = "https://jsonplaceholder.typicode.com/posts";

export function fetchMovies() {
    const { data, error } = useSWR(MOVIE_ENDPOINT, fetcher)
  
    return {
      movies: data as Movie[],
      isLoading: !error && !data,
      isError: error as boolean
    }
  }