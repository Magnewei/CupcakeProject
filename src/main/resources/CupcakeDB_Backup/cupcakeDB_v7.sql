--
-- PostgreSQL database dump
--

-- Dumped from database version 16.2 (Debian 16.2-1.pgdg120+2)
-- Dumped by pg_dump version 16.1

-- Started on 2024-04-04 14:39:42 UTC

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 4 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: pg_database_owner
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO pg_database_owner;

--
-- TOC entry 3415 (class 0 OID 0)
-- Dependencies: 4
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: pg_database_owner
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 215 (class 1259 OID 16718)
-- Name: bottom; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.bottom (
    "bottomID" integer NOT NULL,
    name character varying(30),
    price integer NOT NULL
);


ALTER TABLE public.bottom OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 16721)
-- Name: bottom_bottomID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."bottom_bottomID_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public."bottom_bottomID_seq" OWNER TO postgres;

--
-- TOC entry 3416 (class 0 OID 0)
-- Dependencies: 216
-- Name: bottom_bottomID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."bottom_bottomID_seq" OWNED BY public.bottom."bottomID";


--
-- TOC entry 217 (class 1259 OID 16722)
-- Name: cupcake; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cupcake (
    "cupcakeID" integer NOT NULL,
    "bottomID" integer,
    "toppingID" integer
);


ALTER TABLE public.cupcake OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 16725)
-- Name: cupcake_cupcakeID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."cupcake_cupcakeID_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public."cupcake_cupcakeID_seq" OWNER TO postgres;

--
-- TOC entry 3417 (class 0 OID 0)
-- Dependencies: 218
-- Name: cupcake_cupcakeID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."cupcake_cupcakeID_seq" OWNED BY public.cupcake."cupcakeID";


--
-- TOC entry 219 (class 1259 OID 16726)
-- Name: orderline; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.orderline (
    "orderlineID" integer NOT NULL,
    "cupcakeID" integer NOT NULL,
    amount integer,
    "orderID" integer
);


ALTER TABLE public.orderline OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 16729)
-- Name: orderline_cupcakeID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."orderline_cupcakeID_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public."orderline_cupcakeID_seq" OWNER TO postgres;

--
-- TOC entry 3418 (class 0 OID 0)
-- Dependencies: 220
-- Name: orderline_cupcakeID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."orderline_cupcakeID_seq" OWNED BY public.orderline."cupcakeID";


--
-- TOC entry 221 (class 1259 OID 16730)
-- Name: orderline_orderlineID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."orderline_orderlineID_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public."orderline_orderlineID_seq" OWNER TO postgres;

--
-- TOC entry 3419 (class 0 OID 0)
-- Dependencies: 221
-- Name: orderline_orderlineID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."orderline_orderlineID_seq" OWNED BY public.orderline."orderlineID";


--
-- TOC entry 222 (class 1259 OID 16731)
-- Name: orders; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.orders (
    "orderID" integer NOT NULL,
    "isPaidFor" boolean,
    "userID" integer NOT NULL
);


ALTER TABLE public.orders OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 16734)
-- Name: orders_orderID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."orders_orderID_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public."orders_orderID_seq" OWNER TO postgres;

--
-- TOC entry 3420 (class 0 OID 0)
-- Dependencies: 223
-- Name: orders_orderID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."orders_orderID_seq" OWNED BY public.orders."orderID";


--
-- TOC entry 224 (class 1259 OID 16735)
-- Name: orders_userID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."orders_userID_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public."orders_userID_seq" OWNER TO postgres;

--
-- TOC entry 3421 (class 0 OID 0)
-- Dependencies: 224
-- Name: orders_userID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."orders_userID_seq" OWNED BY public.orders."userID";


--
-- TOC entry 225 (class 1259 OID 16736)
-- Name: topping; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.topping (
    "toppingID" integer NOT NULL,
    name character varying(30),
    price integer NOT NULL
);


ALTER TABLE public.topping OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 16739)
-- Name: topping_toppingID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."topping_toppingID_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public."topping_toppingID_seq" OWNER TO postgres;

--
-- TOC entry 3422 (class 0 OID 0)
-- Dependencies: 226
-- Name: topping_toppingID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."topping_toppingID_seq" OWNED BY public.topping."toppingID";


--
-- TOC entry 227 (class 1259 OID 16740)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    "userID" integer NOT NULL,
    email character varying(255),
    password character varying(255),
    role character varying(8),
    balance integer
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 228 (class 1259 OID 16745)
-- Name: users_userID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."users_userID_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public."users_userID_seq" OWNER TO postgres;

--
-- TOC entry 3423 (class 0 OID 0)
-- Dependencies: 228
-- Name: users_userID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."users_userID_seq" OWNED BY public.users."userID";


--
-- TOC entry 3230 (class 2604 OID 16746)
-- Name: bottom bottomID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bottom ALTER COLUMN "bottomID" SET DEFAULT nextval('public."bottom_bottomID_seq"'::regclass);


--
-- TOC entry 3231 (class 2604 OID 16747)
-- Name: cupcake cupcakeID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cupcake ALTER COLUMN "cupcakeID" SET DEFAULT nextval('public."cupcake_cupcakeID_seq"'::regclass);


--
-- TOC entry 3232 (class 2604 OID 16748)
-- Name: orderline orderlineID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orderline ALTER COLUMN "orderlineID" SET DEFAULT nextval('public."orderline_orderlineID_seq"'::regclass);


--
-- TOC entry 3233 (class 2604 OID 16749)
-- Name: orders orderID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders ALTER COLUMN "orderID" SET DEFAULT nextval('public."orders_orderID_seq"'::regclass);


--
-- TOC entry 3234 (class 2604 OID 16750)
-- Name: topping toppingID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.topping ALTER COLUMN "toppingID" SET DEFAULT nextval('public."topping_toppingID_seq"'::regclass);


--
-- TOC entry 3235 (class 2604 OID 16751)
-- Name: users userID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN "userID" SET DEFAULT nextval('public."users_userID_seq"'::regclass);


--
-- TOC entry 3396 (class 0 OID 16718)
-- Dependencies: 215
-- Data for Name: bottom; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.bottom VALUES (1, 'Almond', 7);
INSERT INTO public.bottom VALUES (2, 'Pistacio', 6);
INSERT INTO public.bottom VALUES (3, 'Nutmeg', 5);
INSERT INTO public.bottom VALUES (4, 'Vanilla', 5);
INSERT INTO public.bottom VALUES (5, 'Chocolate', 5);


--
-- TOC entry 3398 (class 0 OID 16722)
-- Dependencies: 217
-- Data for Name: cupcake; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.cupcake VALUES (1, 1, 1);
INSERT INTO public.cupcake VALUES (2, 1, 2);
INSERT INTO public.cupcake VALUES (3, 1, 3);
INSERT INTO public.cupcake VALUES (4, 1, 4);
INSERT INTO public.cupcake VALUES (5, 1, 5);
INSERT INTO public.cupcake VALUES (6, 1, 6);
INSERT INTO public.cupcake VALUES (7, 1, 7);
INSERT INTO public.cupcake VALUES (8, 1, 8);
INSERT INTO public.cupcake VALUES (9, 1, 9);
INSERT INTO public.cupcake VALUES (10, 2, 1);
INSERT INTO public.cupcake VALUES (11, 2, 2);
INSERT INTO public.cupcake VALUES (12, 2, 3);
INSERT INTO public.cupcake VALUES (13, 2, 4);
INSERT INTO public.cupcake VALUES (14, 2, 5);
INSERT INTO public.cupcake VALUES (15, 2, 6);
INSERT INTO public.cupcake VALUES (16, 2, 7);
INSERT INTO public.cupcake VALUES (17, 2, 8);
INSERT INTO public.cupcake VALUES (18, 2, 9);
INSERT INTO public.cupcake VALUES (19, 3, 1);
INSERT INTO public.cupcake VALUES (20, 3, 2);
INSERT INTO public.cupcake VALUES (21, 3, 3);
INSERT INTO public.cupcake VALUES (22, 3, 4);
INSERT INTO public.cupcake VALUES (23, 3, 5);
INSERT INTO public.cupcake VALUES (24, 3, 6);
INSERT INTO public.cupcake VALUES (25, 3, 7);
INSERT INTO public.cupcake VALUES (26, 3, 8);
INSERT INTO public.cupcake VALUES (27, 3, 9);
INSERT INTO public.cupcake VALUES (28, 4, 1);
INSERT INTO public.cupcake VALUES (29, 4, 2);
INSERT INTO public.cupcake VALUES (30, 4, 3);
INSERT INTO public.cupcake VALUES (31, 4, 4);
INSERT INTO public.cupcake VALUES (32, 4, 5);
INSERT INTO public.cupcake VALUES (33, 4, 6);
INSERT INTO public.cupcake VALUES (34, 4, 7);
INSERT INTO public.cupcake VALUES (35, 4, 8);
INSERT INTO public.cupcake VALUES (36, 4, 9);
INSERT INTO public.cupcake VALUES (37, 5, 1);
INSERT INTO public.cupcake VALUES (38, 5, 2);
INSERT INTO public.cupcake VALUES (39, 5, 3);
INSERT INTO public.cupcake VALUES (40, 5, 4);
INSERT INTO public.cupcake VALUES (41, 5, 5);
INSERT INTO public.cupcake VALUES (42, 5, 6);
INSERT INTO public.cupcake VALUES (43, 5, 7);
INSERT INTO public.cupcake VALUES (44, 5, 8);
INSERT INTO public.cupcake VALUES (45, 5, 9);


--
-- TOC entry 3400 (class 0 OID 16726)
-- Dependencies: 219
-- Data for Name: orderline; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.orderline VALUES (3, 10, 1, 1);
INSERT INTO public.orderline VALUES (4, 23, 4, 1);
INSERT INTO public.orderline VALUES (5, 12, 3, 1);


--
-- TOC entry 3403 (class 0 OID 16731)
-- Dependencies: 222
-- Data for Name: orders; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.orders VALUES (1, false, 1);
INSERT INTO public.orders VALUES (2, false, 1);
INSERT INTO public.orders VALUES (3, false, 1);
INSERT INTO public.orders VALUES (4, false, 1);
INSERT INTO public.orders VALUES (5, false, 1);


--
-- TOC entry 3406 (class 0 OID 16736)
-- Dependencies: 225
-- Data for Name: topping; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.topping VALUES (1, 'Blue cheese', 9);
INSERT INTO public.topping VALUES (2, 'Lemon', 8);
INSERT INTO public.topping VALUES (3, 'Orange', 8);
INSERT INTO public.topping VALUES (4, 'Rum/Raisin', 7);
INSERT INTO public.topping VALUES (5, 'Strawberry', 6);
INSERT INTO public.topping VALUES (6, 'Crispy', 6);
INSERT INTO public.topping VALUES (7, 'Raspberry', 5);
INSERT INTO public.topping VALUES (8, 'Blueberry', 5);
INSERT INTO public.topping VALUES (9, 'Chocolate', 5);


--
-- TOC entry 3408 (class 0 OID 16740)
-- Dependencies: 227
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.users VALUES (1, 'hej', 'hej', 'admin', 3100);
INSERT INTO public.users VALUES (2, 'lol', 'hej', 'bruger', 400);


--
-- TOC entry 3424 (class 0 OID 0)
-- Dependencies: 216
-- Name: bottom_bottomID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."bottom_bottomID_seq"', 5, true);


--
-- TOC entry 3425 (class 0 OID 0)
-- Dependencies: 218
-- Name: cupcake_cupcakeID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."cupcake_cupcakeID_seq"', 45, true);


--
-- TOC entry 3426 (class 0 OID 0)
-- Dependencies: 220
-- Name: orderline_cupcakeID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."orderline_cupcakeID_seq"', 1, false);


--
-- TOC entry 3427 (class 0 OID 0)
-- Dependencies: 221
-- Name: orderline_orderlineID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."orderline_orderlineID_seq"', 5, true);


--
-- TOC entry 3428 (class 0 OID 0)
-- Dependencies: 223
-- Name: orders_orderID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."orders_orderID_seq"', 5, true);


--
-- TOC entry 3429 (class 0 OID 0)
-- Dependencies: 224
-- Name: orders_userID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."orders_userID_seq"', 1, false);


--
-- TOC entry 3430 (class 0 OID 0)
-- Dependencies: 226
-- Name: topping_toppingID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."topping_toppingID_seq"', 9, true);


--
-- TOC entry 3431 (class 0 OID 0)
-- Dependencies: 228
-- Name: users_userID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."users_userID_seq"', 2, true);


--
-- TOC entry 3237 (class 2606 OID 16753)
-- Name: bottom bottom_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bottom
    ADD CONSTRAINT bottom_pkey PRIMARY KEY ("bottomID");


--
-- TOC entry 3239 (class 2606 OID 16755)
-- Name: cupcake cupcake_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cupcake
    ADD CONSTRAINT cupcake_pkey PRIMARY KEY ("cupcakeID");


--
-- TOC entry 3241 (class 2606 OID 16757)
-- Name: orderline orderline_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orderline
    ADD CONSTRAINT orderline_pkey PRIMARY KEY ("orderlineID");


--
-- TOC entry 3243 (class 2606 OID 16759)
-- Name: orders orders_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY ("orderID");


--
-- TOC entry 3245 (class 2606 OID 16761)
-- Name: topping topping_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.topping
    ADD CONSTRAINT topping_pkey PRIMARY KEY ("toppingID");


--
-- TOC entry 3247 (class 2606 OID 16763)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY ("userID");


--
-- TOC entry 3248 (class 2606 OID 16764)
-- Name: cupcake bottomID_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cupcake
    ADD CONSTRAINT "bottomID_fkey" FOREIGN KEY ("bottomID") REFERENCES public.bottom("bottomID");


--
-- TOC entry 3250 (class 2606 OID 16769)
-- Name: orderline cupcakeID_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orderline
    ADD CONSTRAINT "cupcakeID_fkey" FOREIGN KEY ("cupcakeID") REFERENCES public.cupcake("cupcakeID") NOT VALID;


--
-- TOC entry 3251 (class 2606 OID 16774)
-- Name: orderline fk_orderID_orderlineID; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orderline
    ADD CONSTRAINT "fk_orderID_orderlineID" FOREIGN KEY ("orderID") REFERENCES public.orders("orderID") NOT VALID;


--
-- TOC entry 3249 (class 2606 OID 16779)
-- Name: cupcake toppingID_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cupcake
    ADD CONSTRAINT "toppingID_fkey" FOREIGN KEY ("toppingID") REFERENCES public.topping("toppingID");


--
-- TOC entry 3252 (class 2606 OID 16784)
-- Name: orders userID_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT "userID_fkey" FOREIGN KEY ("userID") REFERENCES public.users("userID") NOT VALID;


-- Completed on 2024-04-04 14:39:42 UTC

--
-- PostgreSQL database dump complete
--

