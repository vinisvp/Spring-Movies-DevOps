-- Inserir Gêneros
INSERT INTO genres (name) VALUES ('Ação');
INSERT INTO genres (name) VALUES ('Aventura');
INSERT INTO genres (name) VALUES ('Comédia');
INSERT INTO genres (name) VALUES ('Drama');
INSERT INTO genres (name) VALUES ('Ficção Científica');
INSERT INTO genres (name) VALUES ('Terror');
INSERT INTO genres (name) VALUES ('Romance');
INSERT INTO genres (name) VALUES ('Suspense');

-- Inserir Categorias
INSERT INTO categories (name) VALUES ('Lançamentos');
INSERT INTO categories (name) VALUES ('Populares');
INSERT INTO categories (name) VALUES ('Clássicos');
INSERT INTO categories (name) VALUES ('Família');
INSERT INTO categories (name) VALUES ('Documentário');

-- Inserir Filmes
INSERT INTO movies (title, synopsis, rating, release) VALUES 
('Matrix', 'Um hacker descobre a verdade sobre sua realidade e seu papel na guerra contra seus controladores.', 8.7, '1999-03-31'),
('O Senhor dos Anéis: A Sociedade do Anel', 'Um hobbit recebe a missão de destruir um anel poderoso antes que caia nas mãos erradas.', 8.8, '2001-12-19'),
('Interestelar', 'Uma equipe de exploradores viaja através de um buraco de minhoca no espaço em busca de um novo lar para a humanidade.', 8.6, '2014-11-07'),
('Parasita', 'Uma família pobre se infiltra na vida de uma família rica com consequências inesperadas.', 8.5, '2019-05-30'),
('Vingadores: Ultimato', 'Os Vingadores restantes devem encontrar uma maneira de reverter as ações de Thanos.', 8.4, '2019-04-26'),
('Coringa', 'A origem do icônico vilão do Batman em uma história sombria e perturbadora.', 8.4, '2019-10-04'),
('Inception', 'Um ladrão que rouba segredos corporativos através do uso da tecnologia de compartilhamento de sonhos.', 8.8, '2010-07-16'),
('Pulp Fiction', 'Várias histórias interligadas de crime e redenção em Los Angeles.', 8.9, '1994-10-14');

-- Associar Filmes com Gêneros
INSERT INTO movie_genres (movie_id, genre_id) VALUES (1, 1), (1, 5); -- Matrix: Ação, Ficção Científica
INSERT INTO movie_genres (movie_id, genre_id) VALUES (2, 2), (2, 4); -- LOTR: Aventura, Drama
INSERT INTO movie_genres (movie_id, genre_id) VALUES (3, 2), (3, 5); -- Interestelar: Aventura, Ficção Científica
INSERT INTO movie_genres (movie_id, genre_id) VALUES (4, 4), (4, 8); -- Parasita: Drama, Suspense
INSERT INTO movie_genres (movie_id, genre_id) VALUES (5, 1), (5, 2); -- Vingadores: Ação, Aventura
INSERT INTO movie_genres (movie_id, genre_id) VALUES (6, 4), (6, 8); -- Coringa: Drama, Suspense
INSERT INTO movie_genres (movie_id, genre_id) VALUES (7, 1), (7, 5); -- Inception: Ação, Ficção Científica
INSERT INTO movie_genres (movie_id, genre_id) VALUES (8, 3), (8, 4); -- Pulp Fiction: Comédia, Drama

-- Associar Filmes com Categorias
INSERT INTO movie_categories (movie_id, category_id) VALUES (1, 3); -- Matrix: Clássicos
INSERT INTO movie_categories (movie_id, category_id) VALUES (2, 3), (2, 2); -- LOTR: Clássicos, Populares
INSERT INTO movie_categories (movie_id, category_id) VALUES (3, 2); -- Interestelar: Populares
INSERT INTO movie_categories (movie_id, category_id) VALUES (4, 1), (4, 2); -- Parasita: Lançamentos, Populares
INSERT INTO movie_categories (movie_id, category_id) VALUES (5, 1), (5, 2); -- Vingadores: Lançamentos, Populares
INSERT INTO movie_categories (movie_id, category_id) VALUES (6, 1); -- Coringa: Lançamentos
INSERT INTO movie_categories (movie_id, category_id) VALUES (7, 3), (7, 2); -- Inception: Clássicos, Populares
INSERT INTO movie_categories (movie_id, category_id) VALUES (8, 3); -- Pulp Fiction: Clássicos

-- Inserir Posters dos Filmes
INSERT INTO movie_posters (movie_id, poster_url) VALUES 
(1, 'https://image.tmdb.org/t/p/w500/f89U3ADr1oiB1s9GkdPOEpXUk5H.jpg'),
(2, 'https://image.tmdb.org/t/p/w500/6oom5QYQ2yQTMJIbnvbkBL9cHo6.jpg'),
(3, 'https://image.tmdb.org/t/p/w500/gEU2QniE6E77NI6lCU6MxlNBvIx.jpg'),
(4, 'https://image.tmdb.org/t/p/w500/7IiTTgloJzvGI1TAYymCfbfl3vT.jpg'),
(5, 'https://image.tmdb.org/t/p/w500/or06FN3Dka5tukK1e9sl16pB3iy.jpg'),
(6, 'https://image.tmdb.org/t/p/w500/udDclJoHjfjb8Ekgsd4FDteOkCU.jpg'),
(7, 'https://image.tmdb.org/t/p/w500/9gk7adHYeDvHkCSEqAvQNLV5Uge.jpg'),
(8, 'https://image.tmdb.org/t/p/w500/d5iIlFn5s0ImszYzBPb8JPIfbXD.jpg');

-- Inserir Cliente de Teste
INSERT INTO customers (name, phone, email, password, subscription, expiration, role) VALUES 
('João Silva', '11999999999', 'joao@email.com', '$2a$10$P62/goJOvE6f6XiA2vMZCuxRZvXr9EtoSe3VgEE1xxWeRfPUXwDEu', 'PREMIUM', '2025-12-31', 'CUSTOMER');
-- Senha: Teste@123
