import Link from "next/link";
import LinkButton from "./linkButton";
// used in MenuSection

function UserSection({username}: {username: string}) {
    return (
            <div className="flex">
                <img src="/images/loginIcon.png"/>
                <h2>{username}</h2>
            </div>
    )
}


function MenuSection() {
    return (
        <div>
            <LinkButton text="Movies" link="movies"/>
            <LinkButton text="Bookings" link="Bookings"/>
            <LinkButton text="Schedule" link="Schedule"/>
        </div>
    )
}

function LogOutSection() {
    return (
        <div>
            <Link href="login">
                <img src="/images/loginIcon.png"/>
            </Link>
        </div>
    )
}

export default function Header({username}: {username: string}) {
    return (
        <div className="flex justify-between outline">
            <UserSection username={username}/>
            <MenuSection/>
            <LogOutSection/>
        </div>
    )
}
