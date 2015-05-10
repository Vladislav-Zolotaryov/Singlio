# Singlio
Singleton container for Java!

Usage:

1. Annotate your class with a @Singleton annotatinon 
  (note: it takes a boolean parameter 'lazy' that indicates how to load the singleton)

2. Create and initialize Singlio object
  "Singlio singlio = new Singlio().initialize();"

3. Use it as you will
  "SimpleClass simpleClass = singlio.get(SimpleClass.class);"
