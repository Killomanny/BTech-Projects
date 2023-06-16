-- Users
CREATE TABLE users (
	id SERIAL PRIMARY KEY,
	account_id INTEGER,
	reputation INTEGER NOT NULL,
	views INTEGER DEFAULT 0,
	down_votes INTEGER DEFAULT 0,
	up_votes INTEGER DEFAULT 0,
	display_name VARCHAR(255) NOT NULL,
	location VARCHAR(512),
	profile_image_url VARCHAR(255),
	website_url VARCHAR(255),
	about_me TEXT,
	creation_date TIMESTAMP NOT NULL,
	last_access_date TIMESTAMP NOT NULL
);

-- Posts
CREATE TABLE posts (
	id SERIAL PRIMARY KEY,
	user_id INTEGER NOT NULL,
	post_type_id SMALLINT NOT NULL,
	accepted_answer_id INTEGER,
	score INTEGER NOT NULL,
	parent_id INTEGER,
	view_count INTEGER,
	answer_count INTEGER DEFAULT 0,
	comment_count INTEGER DEFAULT 0,
	title VARCHAR(512),
	tags VARCHAR(512),
	body TEXT NOT NULL,
	favorite_count INTEGER,
	creation_date TIMESTAMP NOT NULL,
	closed_date TIMESTAMP,
	last_edit_date TIMESTAMP,
	last_activity_date TIMESTAMP,
	user_name VARCHAR()
	foreign key (user_id) references users,
	check (post_type_id = 1 or post_type_id = 2) 
);

-- Tags
CREATE TABLE tags (
	id SERIAL PRIMARY KEY,
	tag_name VARCHAR(255) NOT NULL,
	count INTEGER DEFAULT 0
);