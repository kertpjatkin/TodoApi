### General

This is an API to manage todo lists. Currently it has one API endpoint responsible for creating tasks.

### Architecture
The application is based on Hexagonal Architecture ([description](https://blog.ndepend.com/hexagonal-architecture) and [YT video](https://www.youtube.com/watch?v=cPH5AiqLQTo))

The point of the architecture is to create a clear separation between the domain objects, business use cases and the frameworks.

#### `in.pjatkin.todoapi.domain`
This package contains all the relevant domain objects

#### `in.pjatkin.todoapi.application`
This package contains the business logic. Each small business case is in its' own package.

#### `in.pjatkin.todoapi.adapters` 
This is the outermost layer of the application, which is responsible for hooking up frameworks to the application.
For example, the package hooks up Spring Boot controllers to the use cases and AWS SQS notifier to interface `in.pjatkin.todoapi.application.Notifier`

The key point of the architecture is to make sure that the flow of control is from outside in. Hence, `in.pjatkin.todoapi.adapters` depends on `in.pjatkin.todoapi.application`, which, in turn, depends on `in.pjatkin.todoapi.domain`.
There's no other way these packages are allowed to depend on each other.

![img](https://i.imgur.com/7Dv4G9k.png | width=100)

### Error Handling
If uncaught errors propagate to the controller, then the controller has a error handler (`in.pjatk.todoapi.adapters.controllers.helpers`) which logs down the error.
The same error handler is able to propagate the exception with stack trace to Sentry. In order to enable it, specify `sentry.dsn` in `application.properties`
