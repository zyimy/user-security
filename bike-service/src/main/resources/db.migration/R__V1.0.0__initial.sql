  CREATE TABLE IF NOT EXISTS bike
  (
      ID             SERIAL PRIMARY KEY,
      BRAND          VARCHAR(50) NOT NULL,
      MODEL          VARCHAR(50) NOT NULL,
      USERID         VARCHAR(50) NOT NULL
  );