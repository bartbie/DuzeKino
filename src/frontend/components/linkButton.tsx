import Link from "next/link";

export default function LinkButton({link, text}: {link: string, text: string}) {
    return (
        <Link href={link}>
            <button>{text}</button>
        </Link>
    )
}