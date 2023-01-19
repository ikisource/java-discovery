## There are a few additions to sealed classes in Java 16. These are the changes that Java 16 introduces to the sealed class:

- The Java language recognizes sealed, non-sealed, and permits as contextual keywords (similar to abstract and extends)
- Restrict the ability to create local classes that are subclasses of a sealed class (similar to the inability to create anonymous classes of sealed classes).
- Stricter checks when casting sealed classes and classes derived from sealed classes
