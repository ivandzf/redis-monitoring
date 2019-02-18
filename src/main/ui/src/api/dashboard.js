import { interval } from "rxjs";
import { mapTo } from "rxjs/operators";

const fromEventSource = () => {
  const source = interval(1000);
  const example = source.pipe(
    mapTo(_ => {
      new EventSource(process.env.SSE_URL).addEventListener("message", message => {
        return message;
      });
    })
  );

  return example;

  //   const subscribe = example.subscribe(val =>
  //     console.log(`Subscriber One: ${val}`)
  //   );

  // const eventSource = new EventSource(process.env.SSE_URL);
  // eventSource.addEventListener("message", message => {
  //   console.log(message);
  // });

  //   example.connect();
};

export default fromEventSource;
