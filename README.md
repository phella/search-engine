# Search-Engine
Crawler-based search engine.

## Project Modules
The project consists of two main modules:

### Spring boot backend service
Our backend service uses mongodb as database giving better performance on scaling.

| End point     | Parameters    |  usage        |
| ------------- | ------------- | ------------- | 
| /api/pages    | {searchText}, {pageNumber}, {country}  | Return web pages for certain search text| 
| /api/images   | {searchText}, {pageNumber}, {country}  | Return images for certain search text|
| /api/trends   | {country} | Return trends in certian countries |
| /api/suggestions| {searchText} | Return autocomplete options |
| /api/visitedUrls | {searchText} | Return visited urls|

### Search Engine core Divided into 5 modules

#### Crawerler


### Indexer


### Ranker


### Quuery processor


### Analysis Module
