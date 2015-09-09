# Singlio
Singleton container for Java!

[![Build Status](https://api.travis-ci.org/Vladislav-Zolotaryov/Singlio.svg?branch=master)](https://api.travis-ci.org/Vladislav-Zolotaryov/Singlio.svg?branch=master)

Usage:

1. Annotate your class with a @Singleton annotatinon 
  (note: it takes a boolean parameter 'lazy' that indicates how to load the singleton)

2. Create and initialize Singlio object
  "Singlio singlio = new Singlio().initialize();"

3. Use it as you will
  "SimpleClass simpleClass = singlio.get(SimpleClass.class);"
