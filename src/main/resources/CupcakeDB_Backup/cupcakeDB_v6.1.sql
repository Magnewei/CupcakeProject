PGDMP      (    
            |            cupcake    16.2 (Debian 16.2-1.pgdg120+2)    16.1 ;    T           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            U           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            V           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            W           1262    16717    cupcake    DATABASE     r   CREATE DATABASE cupcake WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.utf8';
    DROP DATABASE cupcake;
                postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
                pg_database_owner    false            X           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                   pg_database_owner    false    4            �            1259    16718    bottom    TABLE     |   CREATE TABLE public.bottom (
    "bottomID" integer NOT NULL,
    name character varying(30),
    price integer NOT NULL
);
    DROP TABLE public.bottom;
       public         heap    postgres    false    4            �            1259    16721    bottom_bottomID_seq    SEQUENCE     �   CREATE SEQUENCE public."bottom_bottomID_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public."bottom_bottomID_seq";
       public          postgres    false    215    4            Y           0    0    bottom_bottomID_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public."bottom_bottomID_seq" OWNED BY public.bottom."bottomID";
          public          postgres    false    216            �            1259    16722    cupcake    TABLE     s   CREATE TABLE public.cupcake (
    "cupcakeID" integer NOT NULL,
    "bottomID" integer,
    "toppingID" integer
);
    DROP TABLE public.cupcake;
       public         heap    postgres    false    4            �            1259    16725    cupcake_cupcakeID_seq    SEQUENCE     �   CREATE SEQUENCE public."cupcake_cupcakeID_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public."cupcake_cupcakeID_seq";
       public          postgres    false    4    217            Z           0    0    cupcake_cupcakeID_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public."cupcake_cupcakeID_seq" OWNED BY public.cupcake."cupcakeID";
          public          postgres    false    218            �            1259    16726 	   orderline    TABLE     �   CREATE TABLE public.orderline (
    "orderlineID" integer NOT NULL,
    "cupcakeID" integer NOT NULL,
    amount integer,
    "orderID" integer
);
    DROP TABLE public.orderline;
       public         heap    postgres    false    4            �            1259    16729    orderline_cupcakeID_seq    SEQUENCE     �   CREATE SEQUENCE public."orderline_cupcakeID_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 0   DROP SEQUENCE public."orderline_cupcakeID_seq";
       public          postgres    false    219    4            [           0    0    orderline_cupcakeID_seq    SEQUENCE OWNED BY     W   ALTER SEQUENCE public."orderline_cupcakeID_seq" OWNED BY public.orderline."cupcakeID";
          public          postgres    false    220            �            1259    16730    orderline_orderlineID_seq    SEQUENCE     �   CREATE SEQUENCE public."orderline_orderlineID_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 2   DROP SEQUENCE public."orderline_orderlineID_seq";
       public          postgres    false    219    4            \           0    0    orderline_orderlineID_seq    SEQUENCE OWNED BY     [   ALTER SEQUENCE public."orderline_orderlineID_seq" OWNED BY public.orderline."orderlineID";
          public          postgres    false    221            �            1259    16731    orders    TABLE     w   CREATE TABLE public.orders (
    "orderID" integer NOT NULL,
    "isPaidFor" boolean,
    "userID" integer NOT NULL
);
    DROP TABLE public.orders;
       public         heap    postgres    false    4            �            1259    16734    orders_orderID_seq    SEQUENCE     �   CREATE SEQUENCE public."orders_orderID_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public."orders_orderID_seq";
       public          postgres    false    4    222            ]           0    0    orders_orderID_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public."orders_orderID_seq" OWNED BY public.orders."orderID";
          public          postgres    false    223            �            1259    16735    orders_userID_seq    SEQUENCE     �   CREATE SEQUENCE public."orders_userID_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public."orders_userID_seq";
       public          postgres    false    4    222            ^           0    0    orders_userID_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public."orders_userID_seq" OWNED BY public.orders."userID";
          public          postgres    false    224            �            1259    16736    topping    TABLE     ~   CREATE TABLE public.topping (
    "toppingID" integer NOT NULL,
    name character varying(30),
    price integer NOT NULL
);
    DROP TABLE public.topping;
       public         heap    postgres    false    4            �            1259    16739    topping_toppingID_seq    SEQUENCE     �   CREATE SEQUENCE public."topping_toppingID_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public."topping_toppingID_seq";
       public          postgres    false    225    4            _           0    0    topping_toppingID_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public."topping_toppingID_seq" OWNED BY public.topping."toppingID";
          public          postgres    false    226            �            1259    16740    users    TABLE     �   CREATE TABLE public.users (
    "userID" integer NOT NULL,
    email character varying(255),
    password character varying(255),
    role character varying(8),
    balance integer
);
    DROP TABLE public.users;
       public         heap    postgres    false    4            �            1259    16745    users_userID_seq    SEQUENCE     �   CREATE SEQUENCE public."users_userID_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public."users_userID_seq";
       public          postgres    false    227    4            `           0    0    users_userID_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public."users_userID_seq" OWNED BY public.users."userID";
          public          postgres    false    228            �           2604    16746    bottom bottomID    DEFAULT     v   ALTER TABLE ONLY public.bottom ALTER COLUMN "bottomID" SET DEFAULT nextval('public."bottom_bottomID_seq"'::regclass);
 @   ALTER TABLE public.bottom ALTER COLUMN "bottomID" DROP DEFAULT;
       public          postgres    false    216    215            �           2604    16747    cupcake cupcakeID    DEFAULT     z   ALTER TABLE ONLY public.cupcake ALTER COLUMN "cupcakeID" SET DEFAULT nextval('public."cupcake_cupcakeID_seq"'::regclass);
 B   ALTER TABLE public.cupcake ALTER COLUMN "cupcakeID" DROP DEFAULT;
       public          postgres    false    218    217            �           2604    16748    orderline orderlineID    DEFAULT     �   ALTER TABLE ONLY public.orderline ALTER COLUMN "orderlineID" SET DEFAULT nextval('public."orderline_orderlineID_seq"'::regclass);
 F   ALTER TABLE public.orderline ALTER COLUMN "orderlineID" DROP DEFAULT;
       public          postgres    false    221    219            �           2604    16749    orders orderID    DEFAULT     t   ALTER TABLE ONLY public.orders ALTER COLUMN "orderID" SET DEFAULT nextval('public."orders_orderID_seq"'::regclass);
 ?   ALTER TABLE public.orders ALTER COLUMN "orderID" DROP DEFAULT;
       public          postgres    false    223    222            �           2604    16750    topping toppingID    DEFAULT     z   ALTER TABLE ONLY public.topping ALTER COLUMN "toppingID" SET DEFAULT nextval('public."topping_toppingID_seq"'::regclass);
 B   ALTER TABLE public.topping ALTER COLUMN "toppingID" DROP DEFAULT;
       public          postgres    false    226    225            �           2604    16751    users userID    DEFAULT     p   ALTER TABLE ONLY public.users ALTER COLUMN "userID" SET DEFAULT nextval('public."users_userID_seq"'::regclass);
 =   ALTER TABLE public.users ALTER COLUMN "userID" DROP DEFAULT;
       public          postgres    false    228    227            D          0    16718    bottom 
   TABLE DATA                 public          postgres    false    215   �>       F          0    16722    cupcake 
   TABLE DATA                 public          postgres    false    217   8?       H          0    16726 	   orderline 
   TABLE DATA                 public          postgres    false    219   O@       K          0    16731    orders 
   TABLE DATA                 public          postgres    false    222   �@       N          0    16736    topping 
   TABLE DATA                 public          postgres    false    225   �@       P          0    16740    users 
   TABLE DATA                 public          postgres    false    227   �A       a           0    0    bottom_bottomID_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public."bottom_bottomID_seq"', 5, true);
          public          postgres    false    216            b           0    0    cupcake_cupcakeID_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public."cupcake_cupcakeID_seq"', 45, true);
          public          postgres    false    218            c           0    0    orderline_cupcakeID_seq    SEQUENCE SET     H   SELECT pg_catalog.setval('public."orderline_cupcakeID_seq"', 1, false);
          public          postgres    false    220            d           0    0    orderline_orderlineID_seq    SEQUENCE SET     I   SELECT pg_catalog.setval('public."orderline_orderlineID_seq"', 2, true);
          public          postgres    false    221            e           0    0    orders_orderID_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public."orders_orderID_seq"', 2, true);
          public          postgres    false    223            f           0    0    orders_userID_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public."orders_userID_seq"', 1, false);
          public          postgres    false    224            g           0    0    topping_toppingID_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public."topping_toppingID_seq"', 9, true);
          public          postgres    false    226            h           0    0    users_userID_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public."users_userID_seq"', 2, true);
          public          postgres    false    228            �           2606    16753    bottom bottom_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.bottom
    ADD CONSTRAINT bottom_pkey PRIMARY KEY ("bottomID");
 <   ALTER TABLE ONLY public.bottom DROP CONSTRAINT bottom_pkey;
       public            postgres    false    215            �           2606    16755    cupcake cupcake_pkey 
   CONSTRAINT     [   ALTER TABLE ONLY public.cupcake
    ADD CONSTRAINT cupcake_pkey PRIMARY KEY ("cupcakeID");
 >   ALTER TABLE ONLY public.cupcake DROP CONSTRAINT cupcake_pkey;
       public            postgres    false    217            �           2606    16757    orderline orderline_pkey 
   CONSTRAINT     a   ALTER TABLE ONLY public.orderline
    ADD CONSTRAINT orderline_pkey PRIMARY KEY ("orderlineID");
 B   ALTER TABLE ONLY public.orderline DROP CONSTRAINT orderline_pkey;
       public            postgres    false    219            �           2606    16759    orders orders_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY ("orderID");
 <   ALTER TABLE ONLY public.orders DROP CONSTRAINT orders_pkey;
       public            postgres    false    222            �           2606    16761    topping topping_pkey 
   CONSTRAINT     [   ALTER TABLE ONLY public.topping
    ADD CONSTRAINT topping_pkey PRIMARY KEY ("toppingID");
 >   ALTER TABLE ONLY public.topping DROP CONSTRAINT topping_pkey;
       public            postgres    false    225            �           2606    16763    users users_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY ("userID");
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    227            �           2606    16764    cupcake bottomID_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.cupcake
    ADD CONSTRAINT "bottomID_fkey" FOREIGN KEY ("bottomID") REFERENCES public.bottom("bottomID");
 A   ALTER TABLE ONLY public.cupcake DROP CONSTRAINT "bottomID_fkey";
       public          postgres    false    217    3237    215            �           2606    16769    orderline cupcakeID_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.orderline
    ADD CONSTRAINT "cupcakeID_fkey" FOREIGN KEY ("cupcakeID") REFERENCES public.cupcake("cupcakeID") NOT VALID;
 D   ALTER TABLE ONLY public.orderline DROP CONSTRAINT "cupcakeID_fkey";
       public          postgres    false    217    219    3239            �           2606    16774     orderline fk_orderID_orderlineID    FK CONSTRAINT     �   ALTER TABLE ONLY public.orderline
    ADD CONSTRAINT "fk_orderID_orderlineID" FOREIGN KEY ("orderID") REFERENCES public.orders("orderID") NOT VALID;
 L   ALTER TABLE ONLY public.orderline DROP CONSTRAINT "fk_orderID_orderlineID";
       public          postgres    false    3243    219    222            �           2606    16779    cupcake toppingID_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.cupcake
    ADD CONSTRAINT "toppingID_fkey" FOREIGN KEY ("toppingID") REFERENCES public.topping("toppingID");
 B   ALTER TABLE ONLY public.cupcake DROP CONSTRAINT "toppingID_fkey";
       public          postgres    false    3245    217    225            �           2606    16784    orders userID_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.orders
    ADD CONSTRAINT "userID_fkey" FOREIGN KEY ("userID") REFERENCES public.users("userID") NOT VALID;
 >   ALTER TABLE ONLY public.orders DROP CONSTRAINT "userID_fkey";
       public          postgres    false    227    3247    222            D   y   x���v
Q���W((M��L�K�/)��Us�	uV�0�QPw����KQ�Q0״��$���% ��$193�Ɍ(M�@M~�%���@-�Di1j	K����I$Z�)P�sF~r~NbI*T aVM      F     x��սj�@E�^O�e&h~v%�*�Ap�v�'�7~���r`˯�������p*���Y���ey[���z.��_�cy�My��m7��]��`8���*\n�Ax`x��'��_����I�,�Ұ��4c)�X��i�I�66H�86J�:k��;�K�:nҰ��4��!�xJ�:^�aoҰ�Ұ��-�י��I�a�0iX'\։��u"�a��ҰN4iX'֖׉Q�֙���K�:iҰN�4��!�dJ�:Y��u����B�      H   :   x���v
Q���W((M��L��/JI-���KUs�	uV�0�Q04�Q Q��\\\ �~V      K   @   x���v
Q���W((M��L��/JI-*Vs�	uV�0�QHK�)N�Q0Դ��$��E= �+�      N   �   x����
�@��WqvHҏ��p!��Z�Q:��0�Dw�l�x�Ǘde\T�dUr�{�&!%[x��g\���}�g��C�h;��V���8��HhF�Dr���ͅL1n���K+0s�rR�]�Rr����=�r�	��L�_�3c�z�F-;ш�M�2���`��      P   Y   x���v
Q���W((M��L�+-N-*Vs�	uV�0�QP�H�RGP�)��y@������5�'!��zr�s�LH**MO-�L�&pq 95%(     