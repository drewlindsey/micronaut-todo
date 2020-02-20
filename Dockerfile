FROM oracle/graalvm-ce:19.3.1-java8 as graalvm
COPY . /home/app/todo
WORKDIR /home/app/todo
RUN gu install native-image
RUN native-image --no-server -cp build/libs/todo-*-all.jar

FROM frolvlad/alpine-glibc
EXPOSE 8080
COPY --from=graalvm /home/app/todo /app/todo
RUN apk add --no-cache libstdc++
ENTRYPOINT ["/app/todo/todo","-Xmx68m"]
