package com.lucene;

public class LuceneTest {
    public static void main(String[] args) {
        StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_40);
        Directory index = new RAMDirectory();
         
        IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_40, analyzer);
         
        IndexWriter w = new IndexWriter(index, config);
        addDoc(w, "Lucene in Action", "193398817");
        addDoc(w, "Lucene for Dummies", "55320055Z");
        addDoc(w, "Managing Gigabytes", "55063554A");
        addDoc(w, "The Art of Computer Science", "9900333X");
        w.close();
    }
    
    private static void addDoc(IndexWriter w, String title, String isbn) throws IOException {
        Document doc = new Document();
        doc.add(new TextField("title", title, Field.Store.YES));
        doc.add(new StringField("isbn", isbn, Field.Store.YES));
        w.addDocument(doc);
      }
}
