#ifndef MINIPSEUDTREE
# define MINIPSEUDTREE
enum NodeType {
	NTEMPTY = 0,
	NTINSTLIST = 1,
	NTINST=2,
 	NTVARLIST=3,
 	NTVAR = 4,


	NTNUM   = 201,
	 

	NTPLUS  = 321,
	NTMIN   = 322,
	NTMULT  = 323,
	NTDIV   = 324,
	NTPOW   = 325,
	NTEQ = 326,
	NTAFF = 327,
	NTIF = 555,
	NTTRAIT_OP=556,
	NTTRAIT_CL=557,
	NTEQU=558,
	NTNEQU=559,
	NTVIRG=560,
	NTFOR=561,
	NTFCTAFF=562,
	NTFCTVAR=563,
	NTPRINT=564,
	NTEXFCT=565,
	NTPPETIT=566,
	NTPGRAND=567,
	NTPPETITEG=568,
	NTPGRANDEG=569,


};


typedef struct Node {
	enum NodeType type;
	double val;
	struct Node** children;
	char* var;
} Node;

Node* createNode(int type);

Node* nodeChildren(Node* father, Node *child1, Node *child2);

const char* node2String(Node *node);

void printGraph(Node *root);
void printNode (Node *node,int n);

#endif
