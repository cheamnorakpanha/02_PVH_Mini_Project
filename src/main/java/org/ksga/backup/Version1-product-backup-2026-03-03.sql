--
-- PostgreSQL database dump
--

\restrict 44VSisyWOivri9EJFWnqpy5mEXIqIIabqKh82iTJeqIR64Uetr66wdknsWG8dXu

-- Dumped from database version 18.1
-- Dumped by pg_dump version 18.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
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
-- Name: products; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.products (
    id integer NOT NULL,
    name character varying(250),
    unit_price double precision,
    quantity integer,
    imported_date date DEFAULT CURRENT_DATE
);


ALTER TABLE public.products OWNER TO postgres;

--
-- Name: products_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.products_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.products_id_seq OWNER TO postgres;

--
-- Name: products_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.products_id_seq OWNED BY public.products.id;


--
-- Name: setting; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.setting (
    id integer NOT NULL,
    display_row integer NOT NULL
);


ALTER TABLE public.setting OWNER TO postgres;

--
-- Name: setting_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.setting_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.setting_id_seq OWNER TO postgres;

--
-- Name: setting_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.setting_id_seq OWNED BY public.setting.id;


--
-- Name: products id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products ALTER COLUMN id SET DEFAULT nextval('public.products_id_seq'::regclass);


--
-- Name: setting id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.setting ALTER COLUMN id SET DEFAULT nextval('public.setting_id_seq'::regclass);


--
-- Data for Name: products; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.products (id, name, unit_price, quantity, imported_date) FROM stdin;
1	Laptop	1299.99	45	2026-02-27
2	Monitor 27-inch	299.5	80	2026-02-27
3	Smartphone	899	150	2026-02-27
4	Tablet	450	120	2026-02-27
7	Noise-Canceling Headphones	249.99	85	2026-02-27
8	Smartwatch	199.5	110	2026-02-27
9	External SSD 1TB	135	90	2026-02-27
10	Webcam 1080p	75	65	2026-02-27
11	USB Microphone	110	40	2026-02-27
12	Laser Printer	220	25	2026-02-27
13	Wi-Fi 6 Router	165	50	2026-02-27
14	Gaming Console	499.99	30	2026-02-27
15	Bluetooth Speaker	85	140	2026-02-27
5	mkrt	115	60	2026-02-27
34	Vivo	200	300	2026-03-03
35	ggg   gg	1000	1000	2026-03-03
36	nnnnn	200	34	2026-03-03
\.


--
-- Data for Name: setting; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.setting (id, display_row) FROM stdin;
1	5
\.


--
-- Name: products_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.products_id_seq', 36, true);


--
-- Name: setting_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.setting_id_seq', 1, true);


--
-- Name: products products_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_pkey PRIMARY KEY (id);


--
-- Name: setting setting_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.setting
    ADD CONSTRAINT setting_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

\unrestrict 44VSisyWOivri9EJFWnqpy5mEXIqIIabqKh82iTJeqIR64Uetr66wdknsWG8dXu

