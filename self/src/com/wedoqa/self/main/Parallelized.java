package com.wedoqa.self.main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.runners.Parameterized;
import org.junit.runners.model.RunnerScheduler;

/*
 * Copyright 2013 WeDoQA
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

public class Parallelized extends Parameterized
{
    
    private static class ThreadPoolScheduler implements RunnerScheduler
    {
        private ExecutorService executor; 
        
        public ThreadPoolScheduler()
        {
            String threads = System.getProperty("junit.parallel.threads", "4");
            int numThreads = Integer.parseInt(threads);
            executor = Executors.newFixedThreadPool(numThreads);
        }
        
        @Override
        public void finished()
        {
            executor.shutdown();
            try
            {
                executor.awaitTermination(500, TimeUnit.MINUTES);
            }
            catch (InterruptedException exc)
            {
                throw new RuntimeException(exc);
            }
        }

        @Override
        public void schedule(Runnable childStatement)
        {
            executor.submit(childStatement);
        }
    }

    public Parallelized(Class klass) throws Throwable
    {
        super(klass);
        setScheduler(new ThreadPoolScheduler());
    }
}
