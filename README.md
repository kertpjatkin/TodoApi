### Architecture
The application is based on Hexagonal Architecture ([description](https://blog.ndepend.com/hexagonal-architecture) and [YT video](https://www.youtube.com/watch?v=cPH5AiqLQTo))

The point of the architecture is to create a clear separation between the business use cases and the frameworks. For example, a concrete 

#### `in.pjatkin.todoapi.domain`
This package contains all the relevant domain objects

#### `in.pjatkin.todoapi.application`
This package contains the business logic. Each small business case is in its' own package.

#### `in.pjatkin.todoapi.adapters` 
This is the outermost layer of the application, which is responsible for hooking up frameworks to the application.
For example, the package hooks up Spring Boot controllers to the use cases and AWS SQS notifier to `in.pjatkin.todoapi.application`

The key point of the architecture is to make sure that the flow of control is from outside in. Hence, `in.pjatkin.todoapi.application` depends on `in.pjatkin.todoapi.useCases`, which, in turn, depends on `in.pjatkin.todoapi.domain`.
There's no other way these packages are allowed to depend on each other.

### Error Handling
If uncaught errors propagate to the controller, then the controller has a error handler (`in.pjatk.todoapi.application.controllers.helpers`) which logs down the error.
The same error handler is able to propagate the exception with stack trace to Sentry. In order to enable it, specify `sentry.dsn` in `application.properties`
