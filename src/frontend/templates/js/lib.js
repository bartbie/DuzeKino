// we don't use it, just fun to code

/**
 * for-loop with a non-blocking delay between each iteration
 * @param n {number} number of iterations
 * @param delay {number} milliseconds of delay, 0 - immediately
 * @param func {function} code that will run each iteration, takes current iteration index as an argument
 * @example
 * // prints from 1 to 5 every 100ms
 * forDelay(5, 100, (i) => {
 *     console.log(i)
 * })
 */
export function forDelay(n, delay, func) {
    let i = 0;
    function iteration() {
        if (i < n) {
            func(i);
            i++;
            setTimeout(iteration, delay)
        }
    }
    iteration()
}