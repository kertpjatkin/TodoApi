# Architecture
The application is based on [Hexagonal Architecture](https://blog.ndepend.com/hexagonal-architecture)

#### `in.pjatkin.todoapi.domain`
This package contains all the relevant domain objects

#### `in.pjatkin.todoapi.useCases`
This package contains the business logic. Each small business case is in its' own package.

#### `in.pjatkin.todoapi.application` 
This is the outermost layer of the application, which is responsible for hooking up frameworks to the application.
For example, the package hooks up Spring Boot controllers to the use cases and AWS SQS notifier to ``

The key point of the architecture is to make sure that the flow of control is from outside in. Hence, `in.pjatkin.todoapi.application` depends on `in.pjatkin.todoapi.useCases`, which, in turn, depends on `in.pjatkin.todoapi.domain`.
There's no other way these packages are allowed to depend on each other.   
