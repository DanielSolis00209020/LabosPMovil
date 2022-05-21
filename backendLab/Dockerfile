FROM node:18
# Create app directory
WORKDIR /app
# Install app dependencies
# A wildcard is used to ensure both package.json AND package-lock.json are copied
COPY package*.json ./

RUN npm install
# If you are building your code for production
# RUN npm ci --only=production
# Bundle app source files
COPY . .

EXPOSE 3000
CMD ["node", "bin/www"]
# docker build . -t solis/dummy_dictionary_container
# docker run -p 3000:3000 -d solis/dummy_dictionary_container:latest