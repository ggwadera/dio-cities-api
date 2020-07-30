# Cities API

Project made available by [Digital Innovation One](https://web.digitalinnovation.one/).

This is a Java Spring Rest API for querying Brazilian cities with comparative data, using PostgreSQL to manage the
cities' database.

## How to use

1. Clone this repository:

    ```shell script
    git clone https://github.com/ggwadera/dio-cities-api.git
    cd dio-cities-api
    ```

2. Start the Docker PostgreSQL container using the `docker-compose.yml` file:

    ```shell script
    docker-compose up -d
    ```
   
3. Run the following commands to set up the database with the countries, states and cities (with scripts from [here](https://github.com/chinnonsantos/sql-paises-estados-cidades)):

    ```shell script
    #Download SQL script files
    mkdir sql
    cd sql
    curl -O "https://raw.githubusercontent.com/chinnonsantos/sql-paises-estados-cidades/master/PostgreSQL/{pais,estado,cidade}.sql"
    
    #Enter psql environment inside the running container (password = postgres)
    docker run -it --rm --net=host -v $PWD:/tmp postgres psql -h localhost -U postgres cities
    
    #Inside psql, run the scripts to populate the database, enable extensions, and exit container
    \i /tmp/pais.sql;
    \i /tmp/estado.sql;
    \i /tmp/cidade.sql;
    CREATE EXTENSION cube;
    CREATE EXTENSION earthdistance;
    \q
    ```
   
4. Build and run the server with `gradle`

    ```shell script
    cd ..
    ./gradlew build
    ./gradlew run
    ```
   
5. Access the API on `localhost:8080`

## API entry points

- GET `/countries`
- GET `/states`
- GET `/cities`

    Returns an unsorted paged list of countries, states or cities, respectively, in the database. Parameters:
    
    - `page` - (integer) requested page
    - `size` - (integer) page size (default = 20)
    - `sort` - (string,string) sort by a column, i.e.: `sort=name,desc` to sort by names in descending order (default = unsorted)
    
- GET `/countries/{id}`
- GET `/states/{id}`
- GET `/cities/{id}`

    Returns the country, state or city, respectively, with the corresponding `id`.
    
- GET `/cities/distances/by-points?from={id}&to={id}`

    Returns the distance between two cities in miles, using [Point-Based Earth Distances](https://www.postgresql.org/docs/current/earthdistance.html#id-1.11.7.22.6).
    
- GET `/cities/distances/by-cube?from={id}&to={id}`

    Returns the distance between two cities in meters, using [Cube-Based Earth Distances](https://www.postgresql.org/docs/current/earthdistance.html#id-1.11.7.22.5).