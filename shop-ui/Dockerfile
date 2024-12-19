# Stage 1: Build Angular application
# Use an official Node.js image for building the Angular project
FROM node:20 AS build-stage

# Set the working directory inside the container
WORKDIR /app

# Copy package.json and package-lock.json to install dependencies
COPY package.json package-lock.json ./

# Install dependencies
RUN npm install

# Copy the rest of the application code to the container
COPY . .

# Build the Angular app in production mode
RUN npm run build --prod


# Stage 2: Serve Angular application using Nginx
# Use a lightweight Nginx image
FROM nginx:alpine AS serve-stage

# Remove default Nginx configuration file
RUN rm /etc/nginx/conf.d/default.conf

# Copy custom Nginx configuration file to serve Angular app
COPY nginx.conf /etc/nginx/conf.d/

# Copy the built Angular app from the build stage
COPY --from=build-stage /app/dist/shop-ui/browser /usr/share/nginx/html

# Expose port 80 for serving the app
EXPOSE 80

# Start Nginx server in the foreground
CMD ["nginx", "-g", "daemon off;"]

