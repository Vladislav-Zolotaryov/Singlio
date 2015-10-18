# Singlio
Singleton container for Java!

[![Build Status](https://travis-ci.org/Vladislav-Zolotaryov/Singlio.svg?branch=master)](https://travis-ci.org/Vladislav-Zolotaryov/Singlio)
[![codecov.io](http://codecov.io/github/Vladislav-Zolotaryov/Singlio/coverage.svg?branch=master)](http://codecov.io/github/Vladislav-Zolotaryov/Singlio?branch=master)

Usage:

1. Annotate your class with a @Singleton annotatinon 
  (note: it takes a boolean parameter 'lazy' that indicates how to load the singleton)

2. Create and initialize Singlio object
  "Singlio singlio = new Singlio().initialize();"

3. Use it as you will
  "SimpleClass simpleClass = singlio.get(SimpleClass.class);"
