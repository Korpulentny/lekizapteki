FROM node:12.2.0

WORKDIR /lekizapteki

ENV PATH /lekizapteki/node_modules/.bin:$PATH

COPY e2e/ e2e
COPY src/ src
COPY angular.json angular.json
COPY karma.conf.js karma.conf.js
COPY package.json package.json
COPY package-lock.json package-lock.json
COPY tsconfig.app.json tsconfig.app.json
COPY tsconfig.json tsconfig.json
COPY tsconfig.spec.json tsconfig.spec.json
COPY tslint.json tslint.json

RUN npm install

CMD ng serve --host 0.0.0.0
