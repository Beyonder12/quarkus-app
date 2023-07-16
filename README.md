# My Full Stack Application

This application is a full stack web application built using Quarkus, PostgreSQL, and ReactJS. The application consists of two microservices and a front-end web application.

## Microservices
- **Auth Service**: Handles user registration and login functionalities. It uses JWT authentication for secure access.
- **Product Service**: Currently under development, this service will manage all product related functionalities (adding, updating, deleting).

## Web Application
The front-end web application is built with ReactJS. It provides a user interface for user registration, user login, and (in the future) product management.

## Features

- **User Registration**: Users can create a new account with a unique username and password.
- **User Login**: Users can login to their account using their unique credentials.
- **JWT Authentication**: Implements JWT authentication for secure access to certain parts of the application.
- **Add Product (Future Feature)**: In the future, we plan to add functionality for users to add products.

## Built With

- **Back-End**: The back-end is built with [Quarkus](https://quarkus.io/), a full-stack, Kubernetes-native Java framework made for Java virtual machines (JVMs) and native compilation, optimizing Java specifically for containers and enabling it to become an effective platform for serverless, cloud, and Kubernetes environments.

- **Database**: The data persistence is handled by [PostgreSQL](https://www.postgresql.org/), a powerful, open-source object-relational database system with over 30 years of active development that has earned it a strong reputation for reliability, feature robustness, and performance.

- **Front-End**: The front-end is built with [ReactJS](https://reactjs.org/), a popular JavaScript library for building user interfaces, primarily for single-page applications. It's used for handling the view layer for web and mobile apps and allows developers to create reusable UI components.

## Future Plans

We plan to extend this application by adding new features such as adding, updating, and deleting products.

## Getting Started

Here you will provide instructions for how to get a copy of the project up and running on a local machine for development and testing purposes.

## Deployment

Here you will provide instructions for how to get the project deployed on a live system.


http://localhost:1001/auth-service/api/q/swagger-ui/
http://localhost:1002/product-service/api/q/swagger-ui/
https://htl-leonding-college.github.io/quarkus-docker-gh-actions-demo/
