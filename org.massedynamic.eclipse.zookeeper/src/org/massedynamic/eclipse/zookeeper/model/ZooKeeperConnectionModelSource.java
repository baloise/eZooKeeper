/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.massedynamic.eclipse.zookeeper.model;

import org.massedynamic.eclipse.core.model.AbstractConnectionModelSource;
import org.massedynamic.eclipse.core.model.DataModelSourceException;
import org.massedynamic.eclipse.core.model.IDataModelSource;
import org.massedynamic.eclipse.zookeeper.data.ZooKeeperConnection;
import org.massedynamic.eclipse.zookeeper.data.ZooKeeperConnectionDescriptor;
import org.massedynamic.eclipse.zookeeper.runtime.ZooKeeperConnectionDescriptorFiles;

/**
 * {@link IDataModelSource} for {@link ZooKeeperConnectionModel}.
 * 
 * @author Mark Masse
 */
public class ZooKeeperConnectionModelSource extends
        AbstractConnectionModelSource<ZooKeeperConnectionModel, ZooKeeperConnectionDescriptor, ZooKeeperConnection> {

    /**
     * Constructor.
     *
     * @param files The {@link ZooKeeperConnectionDescriptorFiles} backing this source.
     */
    public ZooKeeperConnectionModelSource(ZooKeeperConnectionDescriptorFiles files) {
        super(files);
    }

    @Override
    public ZooKeeperConnection getData(ZooKeeperConnectionDescriptor descriptor) throws DataModelSourceException {
        return new ZooKeeperConnection(descriptor);
    }

    @Override
    protected ZooKeeperConnectionModel createModelInternal(ZooKeeperConnectionDescriptor descriptor) {
        return new ZooKeeperConnectionModel(descriptor);
    }

}
