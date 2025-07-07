CREATE TABLE locations (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    country TEXT,
    timezone TEXT
);

CREATE TABLE weather_readings (
    id SERIAL PRIMARY KEY,
    location_id INT REFERENCES locations(id),
    temperature FLOAT,
    humidity FLOAT,
    wind_speed FLOAT,
    weather_description TEXT,
    recorded_at TIMESTAMP WITHOUT TIME ZONE DEFAULT now()
);
