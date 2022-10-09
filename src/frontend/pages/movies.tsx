import Header from "../components/header";
import Popup from "../components/popup";
import { fetchMovies } from "../lib/api/movie";

function MovieButton({text, fn}: {text: string, fn: () => any}) {
    return (
        <button onClick={fn}>{text}</button>
    )
}

function MovieCard({title, year, pg, duration, desc, theater}: {title: string, year: number, pg: string, duration: number, desc: string, theater: string}) {
    return (
        <div className="MovieCard">
            <div className="Image"><img src="/images/RegistrationIcon.png"></img></div>
            <div className="rightSideCard grid grid-flow-row">
                <span>{title}</span>
                <div className="flex justify-evenly">
                    <span>{year}</span>
                    <span>|</span>
                    <span>{pg}</span>
                    <span>|</span>
                    <span>{duration}</span>
                </div>
                <span>{desc}</span>
                <span>|</span>
                <span>{theater}</span>
            </div>
            <div>
                <MovieButton text="edit" fn={() => {}}/>
                <MovieButton text="delete" fn={() => {}}/>
            </div>
        </div>
    );
}

function CardsSection() {
    const {movies, isLoading, isError} = fetchMovies();
    if (isLoading) return (<p>Loading...</p>)
    if (isError) return (<p>Failed to load</p>)
    return ( 
        <div>
            {JSON.stringify(movies)}
        </div>
    )
    // return (
    //     <div>
    //         <MovieCard title="test" year={2000} pg="FIFTEEN" duration={120} desc="some description" theater="test theater"/>
    //     </div>
    // )
}

export default function MoviesPage() {
  return (
    <div>
      <Header username="test" />
      <Popup insides={<div>Lorum Ipsum </div>}/>
      <CardsSection/>
    </div>
  );
}
