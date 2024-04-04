--
-- PostgreSQL database dump
--

-- Dumped from database version 16.2 (Debian 16.2-1.pgdg120+2)
-- Dumped by pg_dump version 16.1

-- Started on 2024-04-03 11:21:25 UTC

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 215 (class 1259 OID 25569)
-- Name: bottom; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.bottom (
    "bottomID" integer NOT NULL,
    name character varying(30),
    price integer NOT NULL
);


ALTER TABLE public.bottom OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 25572)
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
-- TOC entry 3415 (class 0 OID 0)
-- Dependencies: 216
-- Name: bottom_bottomID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."bottom_bottomID_seq" OWNED BY public.bottom."bottomID";


--
-- TOC entry 217 (class 1259 OID 25573)
-- Name: cupcake; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cupcake (
    "cupcakeID" integer NOT NULL,
    "bottomID" integer,
    "toppingID" integer,
    price integer
);


ALTER TABLE public.cupcake OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 25576)
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
-- TOC entry 3416 (class 0 OID 0)
-- Dependencies: 218
-- Name: cupcake_cupcakeID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."cupcake_cupcakeID_seq" OWNED BY public.cupcake."cupcakeID";


--
-- TOC entry 219 (class 1259 OID 25577)
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
-- TOC entry 220 (class 1259 OID 25580)
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
-- TOC entry 3417 (class 0 OID 0)
-- Dependencies: 220
-- Name: orderline_cupcakeID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."orderline_cupcakeID_seq" OWNED BY public.orderline."cupcakeID";


--
-- TOC entry 221 (class 1259 OID 25581)
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
-- TOC entry 3418 (class 0 OID 0)
-- Dependencies: 221
-- Name: orderline_orderlineID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."orderline_orderlineID_seq" OWNED BY public.orderline."orderlineID";


--
-- TOC entry 222 (class 1259 OID 25582)
-- Name: orders; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.orders (
    "orderID" integer NOT NULL,
    "isPaidFor" boolean,
    price integer,
    "userID" integer NOT NULL
);


ALTER TABLE public.orders OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 25585)
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
-- TOC entry 3419 (class 0 OID 0)
-- Dependencies: 223
-- Name: orders_orderID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."orders_orderID_seq" OWNED BY public.orders."orderID";


--
-- TOC entry 224 (class 1259 OID 25586)
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
-- TOC entry 3420 (class 0 OID 0)
-- Dependencies: 224
-- Name: orders_userID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."orders_userID_seq" OWNED BY public.orders."userID";


--
-- TOC entry 225 (class 1259 OID 25587)
-- Name: topping; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.topping (
    "toppingID" integer NOT NULL,
    name character varying(30),
    price integer NOT NULL
);


ALTER TABLE public.topping OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 25590)
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
-- TOC entry 3421 (class 0 OID 0)
-- Dependencies: 226
-- Name: topping_toppingID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."topping_toppingID_seq" OWNED BY public.topping."toppingID";


--
-- TOC entry 227 (class 1259 OID 25591)
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
-- TOC entry 228 (class 1259 OID 25596)
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
-- TOC entry 3422 (class 0 OID 0)
-- Dependencies: 228
-- Name: users_userID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."users_userID_seq" OWNED BY public.users."userID";


--
-- TOC entry 3230 (class 2604 OID 25597)
-- Name: bottom bottomID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bottom ALTER COLUMN "bottomID" SET DEFAULT nextval('public."bottom_bottomID_seq"'::regclass);


--
-- TOC entry 3231 (class 2604 OID 25598)
-- Name: cupcake cupcakeID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cupcake ALTER COLUMN "cupcakeID" SET DEFAULT nextval('public."cupcake_cupcakeID_seq"'::regclass);


--
-- TOC entry 3232 (class 2604 OID 25599)
-- Name: orderline orderlineID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orderline ALTER COLUMN "orderlineID" SET DEFAULT nextval('public."orderline_orderlineID_seq"'::regclass);


--
-- TOC entry 3233 (class 2604 OID 25601)
-- Name: orders orderID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders ALTER COLUMN "orderID" SET DEFAULT nextval('public."orders_orderID_seq"'::regclass);


--
-- TOC entry 3234 (class 2604 OID 25603)
-- Name: topping toppingID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.topping ALTER COLUMN "toppingID" SET DEFAULT nextval('public."topping_toppingID_seq"'::regclass);


--
-- TOC entry 3235 (class 2604 OID 25604)
-- Name: users userID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN "userID" SET DEFAULT nextval('public."users_userID_seq"'::regclass);


--
-- TOC entry 3396 (class 0 OID 25569)
-- Dependencies: 215
-- Data for Name: bottom; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.bottom ("bottomID", name, price) VALUES (1, 'Almond', 7);
INSERT INTO public.bottom ("bottomID", name, price) VALUES (2, 'Pistacio', 6);
INSERT INTO public.bottom ("bottomID", name, price) VALUES (3, 'Nutmeg', 5);
INSERT INTO public.bottom ("bottomID", name, price) VALUES (4, 'Vanilla', 5);
INSERT INTO public.bottom ("bottomID", name, price) VALUES (5, 'Chocolate', 5);


--
-- TOC entry 3398 (class 0 OID 25573)
-- Dependencies: 217
-- Data for Name: cupcake; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3400 (class 0 OID 25577)
-- Dependencies: 219
-- Data for Name: orderline; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3403 (class 0 OID 25582)
-- Dependencies: 222
-- Data for Name: orders; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3406 (class 0 OID 25587)
-- Dependencies: 225
-- Data for Name: topping; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.topping ("toppingID", name, price) VALUES (1, 'Blue cheese', 9);
INSERT INTO public.topping ("toppingID", name, price) VALUES (2, 'Lemon', 8);
INSERT INTO public.topping ("toppingID", name, price) VALUES (3, 'Orange', 8);
INSERT INTO public.topping ("toppingID", name, price) VALUES (4, 'Rum/Raisin', 7);
INSERT INTO public.topping ("toppingID", name, price) VALUES (5, 'Strawberry', 6);
INSERT INTO public.topping ("toppingID", name, price) VALUES (6, 'Crispy', 6);
INSERT INTO public.topping ("toppingID", name, price) VALUES (7, 'Raspberry', 5);
INSERT INTO public.topping ("toppingID", name, price) VALUES (8, 'Blueberry', 5);
INSERT INTO public.topping ("toppingID", name, price) VALUES (9, 'Chocolate', 5);


--
-- TOC entry 3408 (class 0 OID 25591)
-- Dependencies: 227
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3423 (class 0 OID 0)
-- Dependencies: 216
-- Name: bottom_bottomID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."bottom_bottomID_seq"', 5, true);


--
-- TOC entry 3424 (class 0 OID 0)
-- Dependencies: 218
-- Name: cupcake_cupcakeID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."cupcake_cupcakeID_seq"', 1, false);


--
-- TOC entry 3425 (class 0 OID 0)
-- Dependencies: 220
-- Name: orderline_cupcakeID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."orderline_cupcakeID_seq"', 1, false);


--
-- TOC entry 3426 (class 0 OID 0)
-- Dependencies: 221
-- Name: orderline_orderlineID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."orderline_orderlineID_seq"', 1, false);


--
-- TOC entry 3427 (class 0 OID 0)
-- Dependencies: 223
-- Name: orders_orderID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."orders_orderID_seq"', 1, false);


--
-- TOC entry 3428 (class 0 OID 0)
-- Dependencies: 224
-- Name: orders_userID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."orders_userID_seq"', 1, false);


--
-- TOC entry 3429 (class 0 OID 0)
-- Dependencies: 226
-- Name: topping_toppingID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."topping_toppingID_seq"', 9, true);


--
-- TOC entry 3430 (class 0 OID 0)
-- Dependencies: 228
-- Name: users_userID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."users_userID_seq"', 1, false);


--
-- TOC entry 3237 (class 2606 OID 25606)
-- Name: bottom bottom_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bottom
    ADD CONSTRAINT bottom_pkey PRIMARY KEY ("bottomID");


--
-- TOC entry 3239 (class 2606 OID 25608)
-- Name: cupcake cupcake_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cupcake
    ADD CONSTRAINT cupcake_pkey PRIMARY KEY ("cupcakeID");


--
-- TOC entry 3241 (class 2606 OID 25610)
-- Name: orderline orderline_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orderline
    ADD CONSTRAINT orderline_pkey PRIMARY KEY ("orderlineID");


--
-- TOC entry 3243 (class 2606 OID 25612)
-- Name: orders orders_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY ("orderID");


--
-- TOC entry 3245 (class 2606 OID 25614)
-- Name: topping topping_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.topping
    ADD CONSTRAINT topping_pkey PRIMARY KEY ("toppingID");


--
-- TOC entry 3247 (class 2606 OID 25616)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY ("userID");


--
-- TOC entry 3248 (class 2606 OID 25617)
-- Name: cupcake bottomID_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cupcake
    ADD CONSTRAINT "bottomID_fkey" FOREIGN KEY ("bottomID") REFERENCES public.bottom("bottomID");


--
-- TOC entry 3250 (class 2606 OID 25622)
-- Name: orderline cupcakeID_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orderline
    ADD CONSTRAINT "cupcakeID_fkey" FOREIGN KEY ("cupcakeID") REFERENCES public.cupcake("cupcakeID") NOT VALID;


--
-- TOC entry 3251 (class 2606 OID 25642)
-- Name: orderline fk_orderID_orderlineID; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orderline
    ADD CONSTRAINT "fk_orderID_orderlineID" FOREIGN KEY ("orderID") REFERENCES public.orders("orderID") NOT VALID;


--
-- TOC entry 3249 (class 2606 OID 25632)
-- Name: cupcake toppingID_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cupcake
    ADD CONSTRAINT "toppingID_fkey" FOREIGN KEY ("toppingID") REFERENCES public.topping("toppingID");


--
-- TOC entry 3252 (class 2606 OID 25637)
-- Name: orders userID_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT "userID_fkey" FOREIGN KEY ("userID") REFERENCES public.users("userID") NOT VALID;


-- Completed on 2024-04-03 11:21:26 UTC

--
-- PostgreSQL database dump complete
--

