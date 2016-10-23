CREATE TABLE tbl_board
(
   bno        int(11) AUTO_INCREMENT,
   title      varchar(200),
   content    text,
   writer     varchar(50),
   regdate    timestamp DEFAULT NOW(),
   viewcnt    int(11) DEFAULT 0,
   PRIMARY KEY(bno)
)