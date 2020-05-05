import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class Indexer {
	
	private final int DOCUMENTS_PER_THREAD = 10;
	private Map <Integer, String> documentsURLs;

	public Indexer ()
	{
		this.documentsURLs = new LinkedHashMap<Integer, String>();
	}
	
	public void getDocumentsURLs() {
		documentsURLs.clear();
		/* TODO : GET Documents URLS From DB */
	}
	
	public void constructIndex() throws InterruptedException {
		getDocumentsURLs();
		
		final int numOfThreads = (int) Math.ceil(documentsURLs.size() / DOCUMENTS_PER_THREAD);
		final Map.Entry<Integer, String>[] documentsEntries = 
				(Map.Entry<Integer, String>[]) documentsURLs.entrySet().toArray(new Map.Entry[documentsURLs.size()]);

		for(int i = 0; i < numOfThreads; i++) 
		{
		    final int startIndex = i * DOCUMENTS_PER_THREAD;
		    final int endIndex = Math.min(documentsURLs.size(), (i + 1) * DOCUMENTS_PER_THREAD);
		    Thread indx = new Thread(new IndexerThread(documentsEntries, startIndex, endIndex)); 
		    indx.start();

		}
}