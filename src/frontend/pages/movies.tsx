import Header from "../components/header";
import Popup from "../components/popup";

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
                <MovieButton text="edit" fn={}/>
                <MovieButton text="delete" fn={}/>
            </div>
        </div>
    )
}

function CardsSection() {
    return (
        <div>
            <MovieCard title="test" year={2000} pg="FIFTEEN" duration={120} desc="some description" theater="test theater"/>
        </div>
    )
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
