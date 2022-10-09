
export type PG = "SEVENTEEN" | "FIFTEEN" | "THIRTEEN" | "ANY" 

export type ISOMinutes = string & {readonly ISOMinutes: unique symbol};

export type Movie = {
    title: string,
    year: number,
    PG: PG,
    duration: ISOMinutes | number,
    description: string,
    theater: string
}

// returns closest backend PG rating string representation for that number
export function toClosest(integer: number): PG {
    if (integer >= 17) {
        return "SEVENTEEN";
    } else if (integer >= 15) {
        return "FIFTEEN";
    } else if (integer >= 13) {
        return "THIRTEEN";
    } else {
        return "ANY";
    }
}

// returns minutes in ISO 6801 standard
export function toISO(minutes: number): ISOMinutes {
    return `PT${minutes}M` as ISOMinutes;
}