import {NextPage} from "next";
import Link from "next/link";
import {ReactElement} from "react";
import {useState} from "react";


function LoginButton() {
    return <button type="submit">Log in</button>
}

function LoginBox() {
    return (
        // <div className="grid h-screen place-items-center">
        <div>
            <h1>Kino XP Theater</h1>
            <form>
                <h2>Log in</h2>
                <br/>
                <input type="text" placeholder="your username" required/>
                <br/>
                <input type="password" placeholder="your password" required/>
                <br/>
                <LoginButton/>
                <br/>
                <Link href="register">Create an Account</Link>
            </form>
        </div>
    )
}


function LoginPage(): ReactElement {
    return (
        <div >
            <LoginBox/>
        </div>
    )
}

export default LoginPage