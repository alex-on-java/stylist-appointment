FROM maven:3.5.3-jdk-8-alpine
WORKDIR /build

# We need this user because embeded Postges doesn't work under root
RUN addgroup -S gr && adduser -S nonroot -G gr
RUN chown nonroot .
USER nonroot

ENTRYPOINT ["mvn"]
CMD ["package"]