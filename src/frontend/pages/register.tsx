import Link from 'next/link';

export default function RegisterPage() {
    return (
        <div>
            <h1>Kino XP Theater</h1>
            <form>
                <h2>Log in</h2>
                <br/>
                <input type="text" placeholder="your username" required/>
                <br/>
                <input type="password" placeholder="your password" required/>
                <br/>
                <br/>
                <Link href="register">Already have an account?</Link>
            </form>
        </div>
    )
}

