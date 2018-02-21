
typedef struct Node {
    enum NodeType type;
    union {
        double val;
        char* var;
        struct Node** children;
    };
}Node;

typedef union YYSTYPE {
    struct Node *node;
} YYSTYPE;
